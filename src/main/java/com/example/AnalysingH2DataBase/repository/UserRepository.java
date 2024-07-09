package com.example.AnalysingH2DataBase.repository;

import com.example.AnalysingH2DataBase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
