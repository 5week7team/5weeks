<<<<<<< HEAD
package com.sparta.project.dto.response;

public class MemberResponseDto {
}
=======
package com.sparta.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
>>>>>>> 8fb9b0cf1eef95e60ba4a35cb15c831a09a3bb98
