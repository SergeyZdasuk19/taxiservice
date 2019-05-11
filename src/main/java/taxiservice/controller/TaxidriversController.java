package taxiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taxiservice.entity.Car;
import taxiservice.entity.Point;
import taxiservice.entity.Taxidriver;
import taxiservice.entity.User;
import taxiservice.repository.CarRepo;
import taxiservice.repository.TaxidriverRepo;
import taxiservice.repository.UserRepo;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/taxidrivers")
public class TaxidriversController {

    @Autowired
    private TaxidriverRepo taxidriverRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping("")
    public String mainTaxidrivers(Model model) {
        Iterable<Taxidriver> taxidrivers = taxidriverRepo.findAll();
        //System.out.println(cars);
        model.addAttribute("taxidrivers", taxidrivers);

        return "taxidriverList";
    }

    @GetMapping("/add")
    public String addTaxidriverForm(Model model) {

        Iterable<Taxidriver> taxidrivers = taxidriverRepo.findAll();

        Iterable<User> users = userRepo.findAll();

        User.findFreeTaxist(users, taxidrivers);

        model.addAttribute("taxists", users);

        Iterable<Car> cars = carRepo.findAll();
;
        Car.findFreeCar(cars, taxidrivers);

        //System.out.println("controller - " + users);
        model.addAttribute("cars", cars);
        return "taxidriverAdd";
    }

    @PostMapping("/add")
    public String addTaxidriver(
            @AuthenticationPrincipal User curUser,
            @RequestParam("carId") Car car,
            @RequestParam("taxistId") User user,
            @RequestParam Boolean status,
            @RequestParam String currentLocation,
            Model model) {

        Taxidriver taxidriver = new Taxidriver(status, new Point(currentLocation), user, car);
        taxidriverRepo.save(taxidriver);
        return "redirect:/admin/taxidrivers";


    }

    @PostMapping("/remove")
    public String removeTaxidriver(
            @AuthenticationPrincipal User user,
            @RequestParam Long taxidriverId,
            Model model
    )
    {

        Taxidriver taxidriver = taxidriverRepo.findById(taxidriverId).orElse(new Taxidriver());
        taxidriverRepo.delete(taxidriver);

        return "redirect:/admin/taxidrivers";
    }

    @GetMapping("/edit/{id}")
    public String editTaxidriverForm(
            @RequestParam(name="message", required = false) String message,
            @PathVariable("id") Long taxidriverId,
            Model model) {

        //if(message!=null && message.equals("errphone")) model.addAttribute("message", "Человек с таким телефоном уже существует");
        Taxidriver taxidriver = taxidriverRepo.findById(taxidriverId).orElse(new Taxidriver());

        model.addAttribute("taxidriver", taxidriver);

        Iterable<Taxidriver> taxidrivers = taxidriverRepo.findAll();

        Iterable<User> users = userRepo.findAll();
        User.findFreeTaxist(users, taxidrivers);


        ((List<User>) users).add(taxidriver.getUser());
        model.addAttribute("taxists", users);

        Iterable<Car> cars = carRepo.findAll();
        Car.findFreeCar(cars, taxidrivers, taxidriver);

        //System.out.println("controller - " + users);
        model.addAttribute("cars", cars);
        return "taxidriverEdit";
    }

    @PostMapping("/edit/{id}")
    public String editTaxidriver(
            @AuthenticationPrincipal User curUser,
            @RequestParam("carId") Car car,
            @RequestParam("taxistId") User user,
            @RequestParam("taxidriverId") Taxidriver taxidriver,
            @RequestParam Boolean status,
            @RequestParam String currentLocation,
            Model model
    )
    {
        taxidriver.setCar(car);
        taxidriver.setUser(user);
        taxidriver.setBusy(status);
        taxidriver.getCurrentPoint().setAddress(currentLocation);
        taxidriverRepo.save(taxidriver);
        //Car car = carRepo.findById(carId).orElse(new Car());


        return "redirect:/admin/taxidrivers";
    }

}