package dto;

import lombok.Builder;
import lombok.Getter;
import org.apache.catalina.User;

@Getter
@Builder
public class UserSearchResponse {

    private Long id;
    private String name;
    private String studentNumber;

    public static UserSearchResponse from(User user) {
        return UserSearchResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .studentNumber(user.getStudentNumber())
                .build();
    }
}