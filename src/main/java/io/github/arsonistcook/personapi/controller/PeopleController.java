package io.github.arsonistcook.personapi.controller;

import io.github.arsonistcook.personapi.dto.ResponseMessageDTO;
import io.github.arsonistcook.personapi.dto.request.PersonDTO;
import io.github.arsonistcook.personapi.entity.Person;
import io.github.arsonistcook.personapi.exception.PersonNotFoundException;
import io.github.arsonistcook.personapi.sepository.PersonRepository;
import io.github.arsonistcook.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    private PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getPeople() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessageDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.create(personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
