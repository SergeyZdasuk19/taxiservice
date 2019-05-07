package taxiservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double distance;
    private Double price;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "departure_point", nullable = false)
    private Point departurePoint;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "arrival_point", nullable = false)
    private Point arrivalPoint;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "taxidriver_id", nullable = false)
    private Taxidriver taxidriver;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Order() {
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
}



