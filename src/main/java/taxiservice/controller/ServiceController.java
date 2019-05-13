package taxiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiservice.entity.User;
import taxiservice.repository.UserRepo;

import java.util.Map;

@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    public String greeting(Model model) {
        return "service";
    }




}