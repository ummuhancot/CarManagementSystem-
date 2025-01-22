package com.tpe.dto;

import com.tpe.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    @NotBlank(message = "Lütfen marka giriniz.")
    @NotNull(message = "Boş gecilemez!!!")
    @Size(min = 2,max = 50,message = "En az 2 karakter girmeniz gerekir")
    private String brand;

    @NotBlank(message = "Lütfen renk giriniz.")
    @NotNull(message = "Boş gecilemez!!!")
    @Size(min = 2,max = 50,message = "En az 2 karakter girmeniz gerekir")
    private String color;

    @NotNull(message = "Boş gecilemez!!!")
    private Integer year;

    public CarDTO(Car car) {
        this.brand = car.getBrand();
        this.color = car.getColor();
        this.year = car.getYear();
    }
}
