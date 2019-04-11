package taxiservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import taxiservice.entity.User;
import taxiservice.repository.UserRepo;


import java.util.Map;


@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "hello";
    }

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal User user, Map<String, Object> model) {

        model.put("user", user);
        return "MainPage";
    }


}