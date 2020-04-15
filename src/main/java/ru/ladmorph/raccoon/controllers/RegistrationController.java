package ru.ladmorph.raccoon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ladmorph.raccoon.model.Author;
import ru.ladmorph.raccoon.service.AuthorService;
import ru.ladmorph.raccoon.service.MailSenderService;

@Controller
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application.yml")
public class RegistrationController {

    @Value("${email.message}")
    private String emailTemplateMessage;

    @Value("${raccoon.activate}")
    private String activateURL;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String handleRegistrationForm(Author author, Model model) {
        if (authorService.usernameAvailability(author.getUsername()))
            model.addAttribute("usernameError", true);

        if (authorService.emailAvailability(author.getEmail()))
            model.addAttribute("emailError", true);

        if (!author.getPassword().equals(author.getRePassword()))
            model.addAttribute("passwordError", true);

        if (authorService.save(author)) { // if the user was added successfully

            mailSenderService.sendMessage(author.getEmail(), "Activation code",
                    emailTemplateMessage + "\n\n" +
                            activateURL + author.getActivationCode());

            return "redirect:/login";

        }
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activateAuthor(@PathVariable String code, Model model) {

        if (authorService.activateAuthor(code))
            return "redirect:/login";
        else
            model.addAttribute("activateCode", true);


        return "login";
    }
}
