package ru.ladmorph.raccoon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ladmorph.raccoon.model.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByUsername(String username);

    Author findByEmail(String email);

    Author findByActivationCode(String code);
}
