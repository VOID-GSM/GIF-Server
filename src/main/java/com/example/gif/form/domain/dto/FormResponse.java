package com.example.gif.form.domain.dto;

import com.example.gif.form.domain.entity.Form;
import com.example.gif.form.domain.entity.FormAnswer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FormResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private LocalDateTime submittedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormAnswer> answers = new ArrayList<>();
}
