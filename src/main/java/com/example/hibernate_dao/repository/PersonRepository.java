package com.example.hibernate_dao.repository;

import com.example.hibernate_dao.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Person.PersonWithNameAndAge> {

    @Query("select p from Person p where p.city_of_living = :city")
    List<Person> findByCity(@Param("city") String city);

    @Query("select p from Person p where p.person.age < :age order by p.person.age")
    List<Person> findByAgeLessThan(@Param("age") int age);

    @Query("select p from Person p where p.person.name = :name " +
            "and p.person.surname = :surname")
    List<Optional<Person>> findByNameAndSurname(@Param("name")String name, @Param("surname")String surname);

}
