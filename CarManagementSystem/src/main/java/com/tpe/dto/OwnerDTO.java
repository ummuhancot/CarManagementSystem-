package com.tpe.dto;

import com.tpe.domain.Car;
import com.tpe.domain.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OwnerDTO {

    @NotBlank(message = "Lütfen gecerli bir isim giriniz!")
    private String name;

    @NotBlank(message = "Lütfen gecerli bir soyisim giriniz!")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Lütfen gecerli bir email adresi giriniz")
//@Email
    private String email;

    private List<CarDTO> carList;

    public OwnerDTO(Owner owner) {
        this.name = owner.getName();
        this.lastName = owner.getLastName();
        this.email = owner.getEmail();
        List<Car> carList1 = owner.getCarList();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : carList1) {
            CarDTO carDTO = new CarDTO(car);
            carDTOList.add(carDTO);
        }
        this.carList = carDTOList;
    }
}
