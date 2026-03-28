package com.example.gif.form.domain.service;

import com.example.gif.form.domain.dto.AnswerRequest;
import com.example.gif.form.domain.dto.FormCreateRequest;
import com.example.gif.form.domain.dto.FormSubmitRequest;
import com.example.gif.form.domain.entity.*;
import com.example.gif.form.domain.repository.FormFieldRepository;
import com.example.gif.form.domain.repository.FormRepository;
import com.example.gif.form.domain.repository.FormSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormRepository formRepository;
    private final FormSubmissionRepository submissionRepository;
    private final FormFieldRepository fieldRepository;
    private final FileService fileService;

    public void createForm(FormCreateRequest request) {
        Form form = Form.builder()
                .title(request.getTitle())
                .deadline(request.getDeadline())
                .build();

        List<FormField> fields = request.getFields().stream()
                .map(f -> FormField.builder()
                        .question(f.getQuestion())
                        .type(f.getType())
                        .form(form)
                        .build())
                .toList();

        form.setFields(fields);
        formRepository.save(form);
    }

    public Form getForm(Long formId) {
        return formRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("폼 없음"));
    }

    public void submit(Long formId, FormSubmitRequest request, List<MultipartFile> files) {

        Form form = formRepository.findById(formId)
                .orElseThrow();

        FormSubmission submission = FormSubmission.builder()
                .form(form)
                .userId(1L)
                .build();

        List<FormAnswer> answers = new ArrayList<>();
        int fileIndex = 0;

        for (AnswerRequest req : request.getAnswers()) {

            FormField field = fieldRepository.findById(req.getFieldId())
                    .orElseThrow();

            FormAnswer answer = new FormAnswer();
            answer.setSubmission(submission);
            answer.setField(field);

            if (field.getType() == FieldType.FILE) {

                if (files == null || fileIndex >= files.size()) {
                    throw new RuntimeException("파일 부족");
                }

                String url = fileService.upload(files.get(fileIndex++));
                answer.setFileUrl(url);

            } else {
                answer.setAnswer(req.getAnswer());
            }

            answers.add(answer);
        }

        submission.setAnswers(answers);
        submissionRepository.save(submission);
    }
}