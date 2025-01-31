package com.tpe.service;

import com.tpe.domain.Car;
import com.tpe.domain.Owner;
import com.tpe.dto.CarDTO;
import com.tpe.exception.CarNotFoundException;
import com.tpe.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    ///1
    public void saveCar(@Valid CarDTO carDTO) {
        Car car = new Car();
        BeanUtils.copyProperties(carDTO, car);
        carRepository.save(car);
    }

    ///2
    public List<CarDTO> listAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : cars) {
            CarDTO carDTO = new CarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    public Car findCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("ID si verilen arac bulunamadi : " + id));
        return car;
    }

    ///3-5
    public CarDTO findCarDTOById(Long id) {
        Car car = findCarById(id);
        return new CarDTO(car);
    }

    ///4
    public void deleteCarById(Long id) {
        Car car = findCarById(id);
        carRepository.delete(car);
    }

    public List<CarDTO> findCarDTOByBrand(String brand) {
        List<Car> carList = carRepository.findCarByBrand(brand);
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : carList){
            CarDTO carDTO = new CarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    ///7
    public Page<CarDTO> getDTOPage(Pageable pageable) {
        Page<Car> carPage = carRepository.findAll(pageable);
        return carPage.map(CarDTO::new);
    }


    ///7
    public Page<CarDTO> getDTOPage1(Pageable pageable) {
        Page<Car> carPage = carRepository.findAll(pageable);
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : carPage) {
            carDTOList.add(new CarDTO(car));
        }
        return new PageImpl<>(carDTOList, pageable, carPage.getTotalElements());
    }


    ///8
    public void updateCarDTOById(Long id, @Valid CarDTO carDTO) {
        Car car = findCarById(id);
        car.setYear(carDTO.getYear());
        car.setColor(carDTO.getColor());
        car.setBrand(carDTO.getBrand());
        carRepository.save(car);
    }


    ///1 ownerService
    public void saveAllCar(List<Car> carList) {
        carRepository.saveAll(carList);
    }

    ///9--->
    public List<CarDTO> filterCarByOwner(
            String name,
            String lastname) {
        List<Car> carList = carRepository.findCarByOwnerNameAndOwnerLastName(name, lastname);
        return carList.stream()
                .map(CarDTO::new)
                .toList();
    }


}

