package com.example.gif.form.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
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

    private Long userId;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private List<FormAnswer> answers;
}
