package com.szs.task.domain.repository;

import com.szs.task.domain.model.entity.UserEntity;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUserIdAndPassword(String userId, String password);

    boolean existsByUserId(String userId);

    UserEntity findByUserId(String userId);

    boolean existsByRegNoAndName(String regNo, String name);

}
