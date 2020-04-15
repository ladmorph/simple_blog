package ru.ladmorph.raccoon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ladmorph.raccoon.dao.AuthorRepository;
import ru.ladmorph.raccoon.model.Author;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping
    public String adminPage(Model model) {
        return "admin";
    }

    @PostMapping
    public String removeAuthor(String email, Model model) {
        Author author = authorRepository.findByEmail(email);

        model.addAttribute("author", author);
        return "admin";
    }

    @PostMapping("/lock")
    public String checkLockedAuthor(@RequestParam Long id) {
        Author author = authorRepository.findById(id).get();

        if (author.isAccountNonLocked())
            author.isAccountNonLocked(false);
        else
            author.isAccountNonLocked(true);

        return "admin";
    }

}
