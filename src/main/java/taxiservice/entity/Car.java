package taxiservice.entity;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(mark, car.mark) &&
                Objects.equals(model, car.model) &&
                Objects.equals(type, car.type) &&
                Objects.equals(seatsnumber, car.seatsnumber) &&
                Objects.equals(govnumber, car.govnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, model, type, seatsnumber, govnumber);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", seatsnumber=" + seatsnumber +
                ", govnumber='" + govnumber + '\'' +
                '}';
    }

    public static void findFreeCar(Iterable<Car> cars, Iterable<Taxidriver> taxidrivers){ //доделать и проверить
        Iterator<Car> iterator = cars.iterator();


        while(iterator.hasNext()){
            Car car = iterator.next();

            Iterator<Taxidriver> iteratorTaxi = taxidrivers.iterator();
            while (iteratorTaxi.hasNext()){

                Taxidriver taxidriver = iteratorTaxi.next();

                if(taxidriver.getCar().getId().equals(car.getId())){
                    iterator.remove();

                    break;
                }

            }

        }

    }

    public static void findFreeCar(Iterable<Car> cars, Iterable<Taxidriver> taxidrivers, Taxidriver taxidriverEdit){ //доделать и проверить
        Iterator<Car> iterator = cars.iterator();


        while(iterator.hasNext()){
            Car car = iterator.next();

            Iterator<Taxidriver> iteratorTaxi = taxidrivers.iterator();
            while (iteratorTaxi.hasNext()){

                Taxidriver taxidriver = iteratorTaxi.next();
                if(!taxidriver.equals(taxidriverEdit) && taxidriver.getCar().getId().equals(car.getId())){
                    iterator.remove();
                    break;
                }

            }

        }

    }
}
