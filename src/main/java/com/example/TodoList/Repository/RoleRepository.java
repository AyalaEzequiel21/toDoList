package com.example.TodoList.Repository;

import com.example.TodoList.Model.ERole;
import com.example.TodoList.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Optional<Role> findByName(ERole name);
    Boolean existsbyName(ERole name);
}
