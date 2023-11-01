package com.example54.demo.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_generator")
    @SequenceGenerator(name = "instructor_generator", allocationSize = 1)
    private Long id;

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" , nullable = false)
    private String lastName;


    public Instructor() {
    }

    public Instructor(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    @OneToMany(mappedBy = "instructor")
    private List<Course> courses = new ArrayList<>();




}
