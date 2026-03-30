package com.example.gif.form.domain.entity;

import com.example.gif.auth.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<FormAnswer> answers = new ArrayList<>();

    public void addAnswer(FormAnswer answer) {
        answers.add(answer);
    }

    public static FormSubmission create(Form form, Long userId) {
        return FormSubmission.builder()
                .form(form)
                .userId(userId)
                .build();
    }
}
