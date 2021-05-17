package io.github.arsonistcook.personapi.controller;

import io.github.arsonistcook.personapi.dto.ResponseMessageDTO;
import io.github.arsonistcook.personapi.dto.request.PersonDTO;
import io.github.arsonistcook.personapi.exception.PersonNotFoundException;
import io.github.arsonistcook.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PeopleController {

    private final PersonService personService;

    @GetMapping
    public List<PersonDTO> getPeople() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseMessageDTO updatePerson(@PathVariable long id, @RequestBody @Valid PersonDTO person) throws PersonNotFoundException {
        return personService.update(id, person);
    }
}
