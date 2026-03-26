package com.example.gif.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileService {

    @Value("${spring.file.upload-dir}")
    private String uploadDir;

    public String upload(MultipartFile file) {

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            File dest = new File(uploadDir + fileName);
            file.transferTo(dest);

            return "/images/" + fileName;

        } catch (java.io.IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }
}
