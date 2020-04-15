package ru.ladmorph.raccoon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ladmorph.raccoon.dao.AuthorRepository;
import ru.ladmorph.raccoon.model.Author;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Author author = authorRepository.findByEmail(email);

        if (author == null)
            throw new UsernameNotFoundException("User doesn't exists");

        return author;
    }
}
