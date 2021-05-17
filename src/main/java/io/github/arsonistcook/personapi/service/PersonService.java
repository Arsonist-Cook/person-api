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
        return createResponseMessage(savedPerson.getId(), "Saved Person with Id: ");
    }

    public List<PersonDTO> findAll() {
        List<Person> listedPeople = personRepository.findAll();
        return listedPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(long id) throws PersonNotFoundException {
        Person searchResult = verifyIfExists(id);

        return personMapper.toDTO(searchResult);
    }

    public void delete(long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public ResponseMessageDTO update(long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);
        return createResponseMessage(updatedPerson.getId(), "Updated Person with Id: ");
    }

    private Person verifyIfExists(long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private ResponseMessageDTO createResponseMessage(long id, String message) {
        return ResponseMessageDTO.builder()
                .message(message + id)
                .build();
    }

}
