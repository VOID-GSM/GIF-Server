package com.example.gif.form.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FormSubmitRequest {
    private List<AnswerRequest> answers;
}
