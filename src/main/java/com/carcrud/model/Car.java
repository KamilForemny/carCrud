package com.carcrud.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    private String brand;

    @Column(nullable = false)
    @NotEmpty
    private String model;

    @Column(nullable = false)
    @NotEmpty
    private String colour;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
    @NotEmpty
    private String registrationPlace;

    public Car() {
    }

    public Car(String brand, String model, String colour, LocalDate registrationDate, String registrationPlace) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.registrationDate = registrationDate;
        this.registrationPlace = registrationPlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationPlace() {
        return registrationPlace;
    }

    public void setRegistrationPlace(String registrationPlace) {
        this.registrationPlace = registrationPlace;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", registrationDate=" + registrationDate +
                ", registrationPlace='" + registrationPlace + '\'' +
                '}';
    }
}
