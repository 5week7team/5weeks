package com.sparta.project.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
<<<<<<< HEAD
import org.springframework.data.annotation.LastModifiedBy;
=======
>>>>>>> 8fb9b0cf1eef95e60ba4a35cb15c831a09a3bb98
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> 8fb9b0cf1eef95e60ba4a35cb15c831a09a3bb98
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeStamped {
<<<<<<< HEAD
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
=======

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
>>>>>>> 8fb9b0cf1eef95e60ba4a35cb15c831a09a3bb98
