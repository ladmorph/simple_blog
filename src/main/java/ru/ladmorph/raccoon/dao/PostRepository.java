package ru.ladmorph.raccoon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ladmorph.raccoon.model.Author;
import ru.ladmorph.raccoon.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthor(Author author);
}
