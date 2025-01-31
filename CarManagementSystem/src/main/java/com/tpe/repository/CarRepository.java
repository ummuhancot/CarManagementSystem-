package com.tpe.repository;

import com.tpe.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarByBrand(String brand);


    ///9--->
    List<Car> findCarByOwnerNameAndOwnerLastName(String ownerName, String ownerLastName);


}
