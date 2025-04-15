package com.jasperexample.jasperemo.repository;

import com.jasperexample.jasperemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
