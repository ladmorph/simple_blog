package ru.ladmorph.raccoon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ladmorph.raccoon.dao.AuthorRepository;
import ru.ladmorph.raccoon.model.Author;
import ru.ladmorph.raccoon.model.Role;

import java.util.Optional;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("{id}")
    public String authorProfile(@PathVariable Long id, @AuthenticationPrincipal Author author, Model model) {
        
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (!optionalAuthor.isPresent()) {
            model.addAttribute("authorUnknown", true);
            return "author";
        }

        if (author != null) {
            if (author.getRoles().contains(Role.ADMIN))
                model.addAttribute("admin", true);
        }

        model.addAttribute("author", optionalAuthor.get());
        model.addAttribute("author", author);

        return "author";
    }

}
