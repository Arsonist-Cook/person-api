package io.github.arsonistcook.personapi.service;

import io.github.arsonistcook.personapi.dto.ResponseMessageDTO;
import io.github.arsonistcook.personapi.dto.request.PersonDTO;
import io.github.arsonistcook.personapi.entity.Person;
import io.github.arsonistcook.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.arsonistcook.personapi.util.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSuccessMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectesSavedPerson = createFakeEntity();

        ResponseMessageDTO expectedMessage = createExpectedResponseMessage(expectesSavedPerson.getId());

        Mockito.when(personRepository.save(any(Person.class))).thenReturn(expectesSavedPerson);

        ResponseMessageDTO receivedMessage = personService.create(personDTO);

        assertEquals(expectedMessage, receivedMessage);
    }

    private ResponseMessageDTO createExpectedResponseMessage(long id) {
        return ResponseMessageDTO.builder()
                .message("Saved Person with Id: " + id)
                .build();
    }
}
