package com.example.gif.project.controller;

import com.example.gif.project.dto.ProjectCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.example.gif.project.service.ProjectService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<?> createProject(
            @AuthenticationPrincipal String providerId,
            @RequestBody ProjectCreateRequest request
    ) {
        Long projectId = projectService.createProject(providerId, request);

        URI location = URI.create("/project/" + projectId);

        return ResponseEntity.created(location).build();
    }
}