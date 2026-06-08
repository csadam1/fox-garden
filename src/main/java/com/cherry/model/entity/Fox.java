package com.cherry.model.entity;

import com.cherry.model.enumerate.Gender;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Fox")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fox {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fox_seq")
    @SequenceGenerator(name = "fox_seq", sequenceName = "fox_seq")
    @Column(nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "species", nullable = false)
    private String species;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Lob
    @Column(name = "image")
    private String image;
}
