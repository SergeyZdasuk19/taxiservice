package taxiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taxiservice.entity.Order;
import taxiservice.entity.Role;
import taxiservice.entity.Taxidriver;
import taxiservice.entity.User;
import taxiservice.repository.OrderRepo;
import taxiservice.repository.TaxidriverRepo;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private TaxidriverRepo taxidriverRepo;

    @GetMapping("")
    public String ordersMain(
            @AuthenticationPrincipal User user,
            Model model) {

        Iterable<Order> orders = orderRepo.findAll();
        Order.findFreeOrder(orders);
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);

        return "orderListMain";
    }

    @GetMapping("/{id}")
    public String userOrders(
            @AuthenticationPrincipal User user,
            Model model) {

        Iterable<Order> orders = orderRepo.findAll();
        Order.findUserOrder(orders, user.getId());
        model.addAttribute("orders", orders);

        return "orderUserList";
    }

    @PostMapping("/remove")
    public String removeOrder(
            @AuthenticationPrincipal User user,
            @RequestParam("orderId") Order order,
            Model model) {

        orderRepo.delete(order);

        return "orderUserList";
    }

    @PostMapping("/takeorder")
    public String takeOrder(
            @AuthenticationPrincipal User user,
            @RequestParam("orderId") Order order,
            Model model) {

        Taxidriver taxidriver = taxidriverRepo.findByUser(user);
        //System.out.println(taxidriver);
        order.setTaxidriver(taxidriver);
        order.setStatus("Заказ принят");
        orderRepo.save(order);

        return "redirect:/orders";
    }

    @GetMapping("taxist/{id}")
    public String taxistOrders(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long userTaxisId,
            Model model) {

        Taxidriver taxidriver = taxidriverRepo.findByUserId(userTaxisId);
        List<Order> orders = orderRepo.findTaxistOrders(taxidriver);
       // Order.findUserOrder(orders, user.getId());
        model.addAttribute("orders", orders);
        model.addAttribute("taxidriver", taxidriver);

        return "orderTaxistList";
    }

    @PostMapping("/status")
    public String orderStatusChange(
            @AuthenticationPrincipal User user,
            @RequestParam("orderId") Order order,
            @RequestParam(name="message", required = false) String message,
            Model model) {

        if (message.equals("cancel")) {
            order.setStatus("Отменён");
            orderRepo.save(order);
        }
        else if(message.equals("comming")){
            order.setStatus("В пути");
            orderRepo.save(order);
        }
        else if(message.equals("here")){
            order.setStatus("Прибыл. Ожидание пассажира");
            orderRepo.save(order);
        }
        else if(message.equals("waiting")){
            order.setStatus("Прибыл. Ожидание пассажира");
            orderRepo.save(order);
        }
        else if(message.equals("successful")){
            order.setStatus("Заказ выполнен");
            orderRepo.save(order);
        }

        if(user.getRoles().contains(Role.USER)) return "redirect:/orders/" + String.valueOf(user.getId());
        else return "redirect:/orders/taxist/" + String.valueOf(user.getId());
    }





}
