package taxiservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mark;
    private String model;
    @Column(name = "seats_number")
    private Integer seatsnumber;
    private String govnumber;

    public Car() {
    }

    public Car(String mark, String model, Integer seatsnumber, String govnumber) {
        this.mark = mark;
        this.model = model;
        this.seatsnumber = seatsnumber;
        this.govnumber = govnumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeatsnumber() {
        return seatsnumber;
    }

    public void setSeatsnumber(Integer seatsnumber) {
        this.seatsnumber = seatsnumber;
    }

    public String getGovnumber() {
        return govnumber;
    }

    public void setGovnumber(String govnumber) {
        this.govnumber = govnumber;
    }
}
