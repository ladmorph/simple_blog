package ru.ladmorph.raccoon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ladmorph.raccoon.dao.AuthorRepository;
import ru.ladmorph.raccoon.model.Author;
import ru.ladmorph.raccoon.model.Role;
import ru.ladmorph.raccoon.service.AuthorService;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean save(Author author) {
        Author authorFromDb = authorRepository.findByUsername(author.getUsername());

        if (authorFromDb != null)
            return false;

        author.setPassword(bCryptPasswordEncoder.encode(author.getPassword()));
        author.setRegDate(new Date().toString());
        author.setRoles(Collections.singleton(Role.USER));
        author.setPosts(Collections.EMPTY_LIST);
        author.setVerified(false);
        author.setActivationCode(UUID.randomUUID().toString());

        authorRepository.save(author);

        return true;
    }

    @Override
    public boolean emailAvailability(String email) {
        Author author = authorRepository.findByEmail(email);

        if (author != null)
            return true;

        return false;
    }

    @Override
    public boolean activateAuthor(String code) {
        Author author = authorRepository.findByActivationCode(code);

        if (author == null)
            return true;

        author.setVerified(true);
        author.setActivationCode(null);

        return false;
    }

    @Override
    public boolean usernameAvailability(String username) {
        Author author = authorRepository.findByUsername(username);

        if (author != null)
            return true;

        return false;
    }
}
