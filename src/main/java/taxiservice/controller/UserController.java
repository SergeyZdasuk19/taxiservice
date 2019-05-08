package taxiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taxiservice.entity.Car;
import taxiservice.entity.Person;
import taxiservice.entity.Role;
import taxiservice.entity.User;
import taxiservice.repository.CarRepo;
import taxiservice.repository.PersonRepo;
import taxiservice.repository.UserRepo;

import java.util.Collections;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("")
    public String mainUsers(
            @RequestParam(name="message", required = false) String message, Model model) {
        Iterable<User> users = userRepo.findAll();
        //System.out.println(cars);
        if(message!=null && message.equals("delerror")) model.addAttribute("message", "Невозможно удалить текущий аккаунт");

        model.addAttribute("users", users);

        return "userList";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {

        return "userAdd";
    }

    @PostMapping("/add")
    public String addUser(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String phonenumber,
            Model model
    )
    {

        User regUser = userRepo.findByUsername(username);

        if (regUser != null) {
            model.addAttribute("message", "User exists!");
            return "userAdd";
        }

        regUser = new User(username, password, true, new Person(name, surname, phonenumber));
        if(role.equals("ADMIN")) regUser.setRoles(Collections.singleton(Role.ADMIN));
        else if(role.equals("USER")) regUser.setRoles(Collections.singleton(Role.USER));
        else regUser.setRoles(Collections.singleton(Role.TAXIDRIVER));
        userRepo.save(regUser);

        return "redirect:/admin/users";
    }

    @PostMapping("/remove")
    public String removeUser(
            @AuthenticationPrincipal User user,
            @RequestParam Long userId,
            Model model
    )
    {
        if (user.getId() == userId) {
            //model.addAttribute("message", "Нельзя удалить текущий аккаунт");
            return "redirect:/admin/users?message=delerror";
        }
        User userDB = userRepo.findById(userId).orElse(new User());
        userRepo.delete(userDB);
        if(userDB.getPerson() != null) personRepo.delete(userDB.getPerson());

        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(
            @RequestParam(name="message", required = false) String message,
            @PathVariable("id") Long userId,
            Model model) {

        if(message!=null && message.equals("errphone")) model.addAttribute("message", "Человек с таким телефоном уже существует");
        User user = userRepo.findById(userId).orElse(new User());
        model.addAttribute("user", user);
        return "userEdit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(
            @AuthenticationPrincipal User userCur,
            @RequestParam("userId") User user,
            @RequestParam("personId") Person person,
            @PathVariable("id") Long userId,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String phoneNumber,
            Model model
    )
    {
       // User userDB = userRepo.findByUsername(username);
        Person personBD = personRepo.findByPhoneNumber(phoneNumber);
        if (personBD != null && !personBD.getPhoneNumber().equals(user.getPerson().getPhoneNumber())) {
            model.addAttribute("message", "Человек с таким телефоном уже существует");
            return "redirect:/admin/users/edit/{id}?message=errphone";
        }

        personRepo.updateAll(user.getPerson().getId(), name, surname, phoneNumber); //important!

        user.setUsername(username);
        user.setPassword(password);

        person.setName(name);
        person.setSurname(surname);
        person.setPhoneNumber(phoneNumber);

        if(role.equals("ADMIN")) user.setRoles(Collections.singleton(Role.ADMIN));
        else if(role.equals("USER")) user.setRoles(Collections.singleton(Role.USER));
        else user.setRoles(Collections.singleton(Role.TAXIDRIVER));

        userRepo.save(user);
        personRepo.save(person);

        return "redirect:/admin/users";
    }
}