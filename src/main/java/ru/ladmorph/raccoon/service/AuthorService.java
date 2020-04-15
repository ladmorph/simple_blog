package ru.ladmorph.raccoon.service;

import ru.ladmorph.raccoon.model.Author;

public interface AuthorService {

    boolean save(Author author);

    /**
     * @param username
     * @return true if author with the given username exist
     */
    boolean usernameAvailability(String username);

    /**
     * @param email
     * @return true if author with the given email exist
     */
    boolean emailAvailability(String email);


    /**
     * @param code
     * @return true if account is not verified
     */
    boolean activateAuthor(String code);
}
