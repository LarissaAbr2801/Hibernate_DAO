package com.example.hibernate_dao.repository;

import com.example.hibernate_dao.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Repository
@RequiredArgsConstructor
public class DaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Person> getPersonsByCity(String city) {
        var cities = List.of("Moscow", "SPB", "ЕКБ", "Иркутск");
        var names = List.of("Ваня", "Петя", "Катя", "Маша", "Андрей");
        var surnames = List.of("Сидоров(-а)", "Петров(-а)", "Иванов(-а)");

        IntStream.range(0, 10)
                .forEach(p -> {
                    Person person = Person.builder()
                            .person(Person.PersonWithNameAndAge.builder()
                                    .name(names.get(new Random().nextInt(names.size())))
                                    .surname(surnames.get(new Random().nextInt(surnames.size())))
                                    .age(new Random().nextInt(1, 100))
                                    .build())
                            .city_of_living(cities.get(new Random().nextInt(cities.size())))
                            .phone_number("+" + p + "1234" + new Random().nextInt(999_999, 2_000_000))
                            .build();
                    entityManager.persist(person);
                });

        return entityManager.createQuery("select p from Person p where p.city_of_living = :city")
                .setParameter("city", city)
                .getResultList();
    }

}
