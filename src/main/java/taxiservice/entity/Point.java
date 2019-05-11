package taxiservice.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double x;
    private Double y;
    private String address;

    public Point() {
    }

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Point(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point(Double x, Double y, String address) {
        this.x = x;
        this.y = y;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(id, point.id) &&
                Objects.equals(x, point.x) &&
                Objects.equals(y, point.y) &&
                Objects.equals(address, point.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, address);
    }
}

