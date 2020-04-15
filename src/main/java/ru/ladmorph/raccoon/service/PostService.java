package ru.ladmorph.raccoon.service;

import ru.ladmorph.raccoon.model.Author;
import ru.ladmorph.raccoon.model.Post;

public interface PostService {

    boolean save(Post post, Author author);
}
