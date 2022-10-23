package com.example.hibernate_dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
public class Person {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class PersonWithNameAndAge implements Serializable {

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String surname;

        @Column(nullable = false)
        private int age;
    }

    @EmbeddedId
    private PersonWithNameAndAge person;

    private String phoneNumber;

    private String cityOfLiving;
}
