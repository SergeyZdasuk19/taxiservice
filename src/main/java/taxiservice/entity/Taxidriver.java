package taxiservice.entity;

import javax.persistence.*;


@Entity
@Table(name = "taxidriver")
public class Taxidriver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "is_busy")
    private boolean isBusy;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "current_point", nullable = false)
    private Point currentPoint;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public Taxidriver() {
    }

    public Taxidriver(boolean isBusy, Point currentPoint, User user, Car car) {
        this.isBusy = isBusy;
        this.currentPoint = currentPoint;
        this.user = user;
        this.car = car;
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

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
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

    @Override
    public String toString() {
        return "Taxidriver{" +
                "id=" + id +
                ", isBusy=" + isBusy +
                ", currentPoint=" + currentPoint +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
}
