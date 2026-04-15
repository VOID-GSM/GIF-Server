#!/bin/bash
set -e

DOMAIN=${1:?"Usage: $0 <domain>"}
SSL_DIR="$(cd "$(dirname "$0")" && pwd)/ssl"

mkdir -p "$SSL_DIR"

# 인증서가 이미 존재하면 만료일 및 도메인 확인
if [ -f "$SSL_DIR/fullchain.pem" ]; then
    EXPIRY=$(openssl x509 -enddate -noout -in "$SSL_DIR/fullchain.pem" | cut -d= -f2)
    EXPIRY_EPOCH=$(date -d "$EXPIRY" +%s 2>/dev/null || date -jf "%b %d %T %Y %Z" "$EXPIRY" +%s)
    NOW_EPOCH=$(date +%s)
    DAYS_LEFT=$(( (EXPIRY_EPOCH - NOW_EPOCH) / 86400 ))
    CERT_CN=$(openssl x509 -subject -noout -in "$SSL_DIR/fullchain.pem" | sed 's/.*CN\s*=\s*//' | cut -d, -f1 | xargs)

    if [ "$DAYS_LEFT" -gt 30 ] && [ "$CERT_CN" = "$DOMAIN" ]; then
        echo "인증서 유효 (${DAYS_LEFT}일 남음, CN=${CERT_CN}). 재생성 생략."
        exit 0
    fi

    echo "인증서 갱신 필요 (만료까지 ${DAYS_LEFT}일, CN=${CERT_CN}, 요청 도메인=${DOMAIN})"
fi

echo "자체 서명 인증서 생성: $DOMAIN"
openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
    -keyout "$SSL_DIR/privkey.pem" \
    -out "$SSL_DIR/fullchain.pem" \
    -subj "/CN=$DOMAIN" \
    -addext "subjectAltName=DNS:$DOMAIN"

chmod 600 "$SSL_DIR/privkey.pem"
echo "인증서 생성 완료: $SSL_DIR"
