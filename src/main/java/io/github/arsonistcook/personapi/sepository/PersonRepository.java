package io.github.arsonistcook.personapi.sepository;

import io.github.arsonistcook.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
