package com.github.Rharhuky.api.repositories;

import com.github.Rharhuky.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
