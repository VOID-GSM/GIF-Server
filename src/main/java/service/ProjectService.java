package service;

import com.example.project.domain.project.dto.ProjectCreateRequest;
import com.example.project.domain.user.User;
import com.example.project.domain.user.UserRepository;
import entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Long createProject(ProjectCreateRequest request) {

        List<User> members = userRepository.findAllById(request.getMemberIds());

        Project project = Project.builder()
                .projectName(request.getProjectName())
                .teamName(request.getTeamName())
                .description(request.getDescription())
                .members(members)
                .build();

        projectRepository.save(project);

        return project.getId();
    }
}