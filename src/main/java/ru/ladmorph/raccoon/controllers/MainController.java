package ru.ladmorph.raccoon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ladmorph.raccoon.dao.PostRepository;
import ru.ladmorph.raccoon.model.Author;
import ru.ladmorph.raccoon.model.Post;
import ru.ladmorph.raccoon.model.Role;
import ru.ladmorph.raccoon.service.PostService;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String mainPage(@AuthenticationPrincipal Author author, Model model) {

        if (author != null) {
            if (author.getRoles().contains(Role.ADMIN))
                model.addAttribute("admin", true);
        }

        model.addAttribute("author", author);
        model.addAttribute("posts", postRepository.findAll());

        return "main";
    }

    @PostMapping
    public String addPost(Post post, @AuthenticationPrincipal Author author) {
        if (author == null) {
            return "redirect:/";
        }
        postService.save(post, author);

        return "redirect:/";
    }
}
