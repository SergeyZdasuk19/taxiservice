package taxiservice.entity;

import javax.persistence.*;
import java.util.Iterator;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mark;
    private String model;
    private String type;
    @Column(name = "seats_number")
    private Integer seatsnumber;
    private String govnumber;

    public Car() {
    }


    public Car(String mark, String model, String type, Integer seatsnumber, String govnumber) {
        this.mark = mark;
        this.model = model;
        this.type = type;
        this.seatsnumber = seatsnumber;
        this.govnumber = govnumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public static void findFreeCar(Iterable<Car> cars, Iterable<Taxidriver> taxidrivers){ //доделать и проверить
        Iterator<Car> iterator = cars.iterator();
        Iterator<Taxidriver> iteratorTaxi = taxidrivers.iterator();

        while(iterator.hasNext()){
            Car car = iterator.next();

            while (iteratorTaxi.hasNext()){

                Taxidriver taxidriver = iteratorTaxi.next();
                if(taxidriver.getCar().getId().equals(car.getId())){
                    iterator.remove();
                    break;
                }

            }


        }

    }
}
