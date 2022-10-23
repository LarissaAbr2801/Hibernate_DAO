package com.example.hibernate_dao.service;

import com.example.hibernate_dao.entity.Person;
import com.example.hibernate_dao.exception.NotFoundException;
import com.example.hibernate_dao.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class DaoService {

    private final PersonRepository personRepository;

    public DaoService(PersonRepository personRepository) {
        this.personRepository = personRepository;

        var cities = List.of("Moscow", "SPB", "ЕКБ", "Иркутск");
        var names = List.of("Ваня", "Петя", "Катя", "Маша", "Андрей");
        var surnames = List.of("Сидоров(-а)", "Петров(-а)", "Иванов(-а)");

        IntStream.range(0, 10).forEach(p -> personRepository.save(Person.builder()
                .person(Person.PersonWithNameAndAge.builder()
                        .name(names.get(new Random().nextInt(names.size())))
                        .surname(surnames.get(new Random().nextInt(surnames.size())))
                        .age(new Random().nextInt(1, 100)).build())
                .cityOfLiving(cities.get(new Random().nextInt(cities.size())))
                .phoneNumber("+" + p + "1234" + new Random().nextInt(999_999, 2_000_000))
                .build()));
    }

    public List<Person> findByCity(String city) {
        return personRepository.findByCityOfLiving(city);
    }

    public List<Person> findByAgeLessThan(int age) {
        return personRepository.findByPersonAgeLessThan(age);
    }

    public List<Optional<Person>> findByNameAndSurname(String name, String surname) {
        List<Optional<Person>> persons = personRepository.findByPersonNameAndPersonSurname(name,
                surname);
        if (persons.isEmpty()) {
            throw new NotFoundException("Ни одной записи не найдено!");
        }
        return persons;
    }
}