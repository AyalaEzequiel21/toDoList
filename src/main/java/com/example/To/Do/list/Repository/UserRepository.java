package com.example.To.Do.list.Repository;

import com.example.To.Do.list.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
