package com.example.hibernate_dao.controller;

import com.example.hibernate_dao.entity.Person;
import com.example.hibernate_dao.repository.DaoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class DaoController {

    private final DaoRepository daoRepository;

    public DaoController(DaoRepository daoRepository) {
        this.daoRepository = daoRepository;
    }

    @GetMapping("persons/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return daoRepository.getPersonsByCity(city);
    }
}
