<<<<<<< HEAD
package com.sparta.project.repository;

public class MemberRepository {
}
=======
package com.sparta.project.repository;


import java.util.Optional;

import com.sparta.project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);
    Optional<Member> findByNickname(String nickname);
}
>>>>>>> 8fb9b0cf1eef95e60ba4a35cb15c831a09a3bb98
