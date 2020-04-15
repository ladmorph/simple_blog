package ru.ladmorph.raccoon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ladmorph.raccoon.dao.PostRepository;
import ru.ladmorph.raccoon.model.Author;
import ru.ladmorph.raccoon.model.Post;
import ru.ladmorph.raccoon.service.PostService;

import java.time.LocalDate;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public boolean save(Post post, Author author) {
        post.setAuthor(author);
        post.setCreationDate(LocalDate.now().toString());
        postRepository.save(post);
        return true;
    }
}
