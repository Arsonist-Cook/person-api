package io.github.arsonistcook.personapi.service;

import io.github.arsonistcook.personapi.dto.ResponseMessageDTO;
import io.github.arsonistcook.personapi.dto.request.PersonDTO;
import io.github.arsonistcook.personapi.entity.Person;
import io.github.arsonistcook.personapi.exception.PersonNotFoundException;
import io.github.arsonistcook.personapi.mapper.PersonMapper;
import io.github.arsonistcook.personapi.sepository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseMessageDTO create(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return ResponseMessageDTO.builder()
                .message("Saved Person with Id: " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> findAll() {
        List<Person> listedPeople = personRepository.findAll();
        return listedPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(long id) throws PersonNotFoundException {

        Person searchResult = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(searchResult);
    }
}
