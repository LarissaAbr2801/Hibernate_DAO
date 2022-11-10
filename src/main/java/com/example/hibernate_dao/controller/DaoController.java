package com.example.hibernate_dao.controller;

import com.example.hibernate_dao.entity.Person;
import com.example.hibernate_dao.service.DaoService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/persons")
public class DaoController {

    private final DaoService daoService;

    public DaoController(DaoService daoService) {
        this.daoService = daoService;
    }

    @GetMapping("/by-city")
    public List<Person> finsPersonsByCity(@RequestParam String city) {
        return daoService.findByCity(city);
    }

    @Secured({"ROLE_BY-AGE"})
    @GetMapping("/by-age")
    public List<Person> findPersonsByAge(@RequestParam int age) {
        return daoService.findByAgeLessThan(age);
    }

    @Secured({"ROLE_BY-NAME-AND-SURNAME"})
    @GetMapping("/by-nameAndSurname")
    public List<Optional<Person>> findPersonByNameAndSurname(@RequestParam String name,
                                                             @RequestParam String surname) {
        return daoService.findByNameAndSurname(name, surname);
    }
}
