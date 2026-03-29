package com.example.gif.form.domain.service;

import com.example.gif.form.domain.dto.FormCreateRequest;
import com.example.gif.form.domain.dto.FormSubmitRequest;
import com.example.gif.form.domain.entity.*;
import com.example.gif.form.domain.repository.FormFieldRepository;
import com.example.gif.form.domain.repository.FormRepository;
import com.example.gif.form.domain.repository.FormSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
                .createdBy("admin")
                .build();

        for (FormCreateRequest.FieldDto f : request.getFields()) {
            FormField field = FormField.builder()
                    .question(f.getQuestion())
                    .type(f.getType())
                    .build();

            form.addField(field);
        }

        formRepository.save(form);
    }

    public Form getForm(Long formId) {
        return formRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("양식이 존재하지 않습니다"));
    }

    public void submit(Long formId, FormSubmitRequest request, List<MultipartFile> files) {

        Form form = formRepository.findById(formId)
                .orElseThrow();

        FormSubmission submission = FormSubmission.create(form, request.getUserId());

        for (int i = 0; i < request.getAnswers().size(); i++) {

            FormSubmitRequest.AnswerDto dto = request.getAnswers().get(i);

            String fileUrl = null;

            if (files != null && files.size() > i && !files.get(i).isEmpty()) {
                fileUrl = fileService.upload(files.get(i));
            }

            FormField field = fieldRepository.findById(dto.getFieldId())
                    .orElseThrow(() -> new RuntimeException("필드가 존재하지 않습니다"));

            FormAnswer answer = FormAnswer.create(
                    dto.getAnswer(),
                    fileUrl,
                    submission,
                    field
            );

            submission.addAnswer(answer);
        }

        submissionRepository.save(submission);
    }
}