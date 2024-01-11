package com.lygin.beekeepersshopapi.repositories;

import com.lygin.beekeepersshopapi.entity.Role;
import com.lygin.beekeepersshopapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
