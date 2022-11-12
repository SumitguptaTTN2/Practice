package com.example.demo.manyTomany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammerRepo extends JpaRepository<Programmer,Integer> {
}
