package repository;

import dto.UserSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@GetMapping("/users/search")
public List<UserSearchResponse> search(
        @RequestParam String keyword
) {
    return userRepository
            .findByStudentNumberContainingOrNameContaining(keyword, keyword)
            .stream()
            .map(UserSearchResponse::from)
            .toList();
}
