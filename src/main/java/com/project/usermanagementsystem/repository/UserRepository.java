package com.project.usermanagementsystem.repository;

import com.project.usermanagementsystem.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    //User findById(Long id);
}
