package taxiservice.entity;

import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.Iterator;

@Entity
@Table(name = "order_taxi")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "departure_point", nullable = true)
    private Point departurePoint;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "arrival_point", nullable = true)
    private Point arrivalPoint;

    private Double distance;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "taxidriver_id", nullable = true)
    private Taxidriver taxidriver;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    private String status;

    public Order() {
    }

    public Order(Point departurePoint, Point arrivalPoint, Double distance, Double price, Taxidriver taxidriver, User user, String status) {
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.distance = distance;
        this.price = price;
        this.taxidriver = taxidriver;
        this.user = user;
        this.status = status;
    }

    public Order(Point departurePoint, Point arrivalPoint, Double distance, Double price, String status) {
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.distance = distance;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Point getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(Point departurePoint) {
        this.departurePoint = departurePoint;
    }

    public Point getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(Point arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public Taxidriver getTaxidriver() {
        return taxidriver;
    }

    public void setTaxidriver(Taxidriver taxidriver) {
        this.taxidriver = taxidriver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", distance=" + distance +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", departurePoint=" + departurePoint +
                ", arrivalPoint=" + arrivalPoint +
                ", taxidriver=" + taxidriver +
                ", user=" + user +
                '}';
    }

    public static void findUserOrder(Iterable<Order> orders, Long userId){ //доделать и проверить
        Iterator<Order> iterator = orders.iterator();

        while(iterator.hasNext()){
            Order order = iterator.next();

            if(order.getUser()==null || !order.getUser().getId().equals(userId)) iterator.remove();

        }

    }

    public static void findFreeOrder(Iterable<Order> orders){ //доделать и проверить
        Iterator<Order> iterator = orders.iterator();

        while(iterator.hasNext()){
            Order order = iterator.next();

            if(order.getTaxidriver() != null || !order.getStatus().equals("Ожидает принятия")) iterator.remove();

        }

    }
}



