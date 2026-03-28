package com.example.gif.form.domain.entity;

import com.example.gif.form.domain.dto.FormResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private String answer;
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private FormSubmission submission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id")
    private FormResponse response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private FormField field;
}
