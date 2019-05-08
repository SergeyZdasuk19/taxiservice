package taxiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taxiservice.entity.Car;
import taxiservice.entity.User;
import taxiservice.repository.CarRepo;
import taxiservice.repository.UserRepo;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/cars")
public class CarsController {

    @Autowired
    private CarRepo carRepo;

    @GetMapping("")
    public String mainCars(Model model) {
        Iterable<Car> cars = carRepo.findAll();
        //System.out.println(cars);
        model.addAttribute("cars", cars);

        return "carsList";
    }

    @GetMapping("/add")
    public String addCarForm(Model model) {

        return "carsAdd";
    }

    @GetMapping("/edit/{id}")
    public String editCarForm(@PathVariable("id") Long carId, Model model) {

        Car car = carRepo.findById(carId).orElse(new Car());
        model.addAttribute("car", car);
        return "carEdit";
    }

    @PostMapping("/add")
    public String addCar(
            @AuthenticationPrincipal User user,
            @RequestParam String mark,
            @RequestParam String carmodel,
            @RequestParam String type,
            @RequestParam Integer seats,
            @RequestParam String govnumber,
            Model model
    )
    {

        Car car = new Car(mark, carmodel, type, seats, govnumber);
        carRepo.save(car);
        return "redirect:/admin/cars";
    }

    @PostMapping("/remove")
    public String removeCar(
            @AuthenticationPrincipal User user,
            @RequestParam Long carId,
            Model model
    )
    {
        Car car = carRepo.findById(carId).orElse(new Car());
        carRepo.delete(car);

        return "redirect:/admin/cars";
    }

    @PostMapping("/edit/{id}")
    public String editCar(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long carId,
            @RequestParam String mark,
            @RequestParam String carmodel,
            @RequestParam String type,
            @RequestParam Integer seats,
            @RequestParam String govnumber,
            Model model
    )
    {
        //Car car = carRepo.findById(carId).orElse(new Car());
        carRepo.updateAll(carId, mark, carmodel, type, seats, govnumber);

        return "redirect:/admin/cars";
    }






}