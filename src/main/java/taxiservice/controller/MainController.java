package taxiservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import taxiservice.entity.Order;
import taxiservice.entity.Point;
import taxiservice.entity.Taxidriver;
import taxiservice.entity.User;
import taxiservice.repository.OrderRepo;
import taxiservice.repository.TaxidriverRepo;
import taxiservice.repository.UserRepo;


import java.util.Map;


@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private TaxidriverRepo taxidriverRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "hello";
    }

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);
        return "MainPage.html";
    }

    @PostMapping("/main")
    public String makeOrder(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String placeFrom,
            @RequestParam String placeTo,
            @RequestParam Double price,
            Model model) {

        //Iterable<Taxidriver> taxidrivers = taxidriverRepo.findAll();
       // Taxidriver taxidriver = taxidrivers.iterator().next();

        User curUser = userRepo.findByUsername(user.getUsername());
        Order order = new Order(new Point(placeFrom), new Point(placeTo), null, price, null, curUser, "Ожидает принятия");
        orderRepo.save(order);
        System.out.println(order);
        model.addAttribute("user", user);
        return "MainPage.html";
    }


}