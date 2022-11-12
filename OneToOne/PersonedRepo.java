package com.example.demo.OneToOne;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonedRepo extends JpaRepository<License,Integer> {
}
