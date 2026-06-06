package com.cherry.model.entity;

import com.cherry.model.enumerate.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "species", nullable = false)
    private String species;

    @Enumerated
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Lob
    @Column(name = "image")
    private String image;
}
