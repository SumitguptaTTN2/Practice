package com.example.demo.manyTomany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    private String pName;
    @ManyToMany(mappedBy = "projects")
    private Set<Programmer> programmerSet;

}
