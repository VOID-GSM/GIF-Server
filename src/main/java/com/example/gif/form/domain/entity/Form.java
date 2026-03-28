package com.example.gif.form.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate deadline;

    private String createdBy;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormField> fields = new ArrayList<>();
}