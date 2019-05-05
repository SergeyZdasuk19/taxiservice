package taxiservice.entity;

import javax.persistence.*;


@Entity
@Table(name = "taxidriver")
public class Taxidriver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isBusy;
    private Double currentX;
    private Double currentY;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public Taxidriver() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(Double currentX) {
        this.currentX = currentX;
    }

    public Double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(Double currentY) {
        this.currentY = currentY;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
