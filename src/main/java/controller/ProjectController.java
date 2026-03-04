package controller;

import com.example.project.domain.project.dto.ProjectCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProjectService;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Long> createProject(
            @RequestBody ProjectCreateRequest request
    ) {
        Long projectId = projectService.createProject(request);
        return ResponseEntity.ok(projectId);
    }
}