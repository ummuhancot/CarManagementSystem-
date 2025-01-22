package com.tpe.service;

import com.tpe.domain.Car;
import com.tpe.domain.Owner;
import com.tpe.dto.CarDTO;
import com.tpe.dto.OwnerDTO;
import com.tpe.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {


    private final OwnerRepository ownerRepository;
    private final CarService carService;

    ///
    @Transactional
    public void saveOwner(@Valid OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setName(ownerDTO.getName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setEmail(ownerDTO.getEmail());
        List<CarDTO> carDTOList = ownerDTO.getCarDTOList();
        List<Car> carList = new ArrayList<>();
        for (CarDTO carDTO : carDTOList) {
            Car car = new Car();
            car.setBrand(carDTO.getBrand());
            car.setColor(carDTO.getColor());
            car.setYear(carDTO.getYear());
            car.setOwner(owner);
            carList.add(car);
        }
        ownerRepository.save(owner);
        carService.saveAllCar(carList);
    }

    ///2
    public List<OwnerDTO> getAllOwnersDTO() {
        List<Owner> ownerList = ownerRepository.findAll();
    /*List<OwnerDTO> ownerDTOList = new ArrayList<>();


    for (Owner owner : ownerList) {
       OwnerDTO ownerDTO = new OwnerDTO(owner);
       ownerDTOList.add(ownerDTO);
    }
    return ownerDTOList;*/

        return ownerList.stream().map(OwnerDTO::new).collect(Collectors.toList());


    }
}
