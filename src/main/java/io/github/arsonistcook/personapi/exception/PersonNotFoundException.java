package io.github.arsonistcook.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public PersonNotFoundException(long id) {
        super("No person found searching by ID=" + id);
    }
}
