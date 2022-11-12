package com.example.demo.OneTomanyMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonORepo extends CrudRepository<PersonO,Integer> {
}
