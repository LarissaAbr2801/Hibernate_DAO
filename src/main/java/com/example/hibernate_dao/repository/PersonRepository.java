package com.example.hibernate_dao.repository;

import com.example.hibernate_dao.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Person.PersonWithNameAndAge> {

    List<Person> findByCityOfLiving(String c);

    List<Person> findByPersonAgeLessThan(int age);

    List<Optional<Person>> findByPersonNameAndPersonSurname(String name, String surname);

}
