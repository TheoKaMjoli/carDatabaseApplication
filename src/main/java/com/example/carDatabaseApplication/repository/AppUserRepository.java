package com.example.carDatabaseApplication.repository;

import com.example.carDatabaseApplication.domain.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long>{

    Optional<AppUser> findByUsername(String username);
}
