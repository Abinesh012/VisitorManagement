package com.quinbay.visitors.repository;

import com.quinbay.visitors.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByEmailAndPassword(String email,String password);


}
