package taxiservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import taxiservice.entity.Person;
import taxiservice.entity.Role;
import taxiservice.entity.User;
import taxiservice.repository.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          @RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam String phoneNumber,
                          Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPerson(new Person(name, surname, phoneNumber));
        userRepo.save(user);

        return "redirect:/login";
    }
}