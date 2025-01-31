package com.tpe.service;

import com.tpe.domain.Car;
import com.tpe.domain.Owner;
import com.tpe.dto.CarDTO;
import com.tpe.dto.OwnerDTO;
import com.tpe.exception.OwnerNotFoundException;
import com.tpe.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    ///1
    @Transactional
    public void saveOwner(
            @Valid OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setName(ownerDTO.getName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setEmail(ownerDTO.getEmail());
        List<CarDTO> carDTOList = ownerDTO.getCarList();
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

    /// 3
    public Owner getOwnerById(
            Long id) {
        Owner foundOwner = ownerRepository.findById(id)
                .orElseThrow(()->new OwnerNotFoundException("Owner not found"));
        return foundOwner;
    }
    /// 3
    public OwnerDTO getOwnerDTOById(Long id) {
        Owner foundOwner = getOwnerById(id);
        return new OwnerDTO(foundOwner);
    }


    ///4---> burda car serviceyi ekledik.
    public void addCarToOwner(Long carId, Long ownerId) {
        Car foundCar=carService.findCarById(carId);
        Owner foundOwner = getOwnerById(ownerId);
        foundCar.setOwner(foundOwner);
        ownerRepository.save(foundOwner);
    }
    /* böylede oluyor.
            public void addCarToOwner(Long carId, Long ownerId) {
            Car foundCar = carService.findCarById(carId);
            Owner foundOwner = getOwnerById(ownerId);
            foundOwner.getCarList().add(foundCar);
            foundCar.setOwner(foundOwner);
            ownerRepository.save(foundOwner);
    }*/
    public void sellCarFromOwner(Long carId, Long ownerId) {

        Car foundCar=carService.findCarById(carId);
        Owner foundOwner = getOwnerById(ownerId);
        foundCar.setOwner(null);
        ownerRepository.save(foundOwner);

    }








}
