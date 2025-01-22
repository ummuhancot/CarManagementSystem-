package com.tpe.controller;

import com.tpe.domain.Car;
import com.tpe.dto.CarDTO;
import com.tpe.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    ///1
    //Araba Kaydet
    //HTTP Yöntemi: POST
    //URL: http://localhost:8080/cars
    @PostMapping
    public ResponseEntity<String> createCar(@Valid @RequestBody CarDTO carDTO) {
        carService.saveCar(carDTO);
        return new ResponseEntity<>("Arac basariyla kaydedildi", HttpStatus.CREATED);
    }

    ///2
    //Tüm Arabaları Getir
    //HTTP Yöntemi: GET
    //URL: http://localhost:8080/cars
    @GetMapping("")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> carDTO = carService.listAllCars();
        return ResponseEntity.ok(carDTO);
    }

    ///3
    //ID ile Araba Getir
    //HTTP Yöntemi: GET
    //URL: http://localhost:8080/cars/2*/
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id) {
        CarDTO carDTO = carService.findCarDTOById(id);
        return ResponseEntity.ok(carDTO);
    }

    ///4
    //ID ile Araba Sil
    //HTTP Yöntemi: DELETE
    //URL: http://localhost:8080/cars/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarWithPathParam(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.ok("Arac basariyla silindi.");
    }

    ///5
    //RequestParam Kullanarak ID ile Araba Getir
    //HTTP Yöntemi: GET
    //URL: http://localhost:8080/cars/q?id=2
    @GetMapping("/q")
    public ResponseEntity<CarDTO> getCarByIdRequestParam(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(carService.findCarDTOById(id));
    }

    ///6
    //RequestParam Kullanarak brand Göre Araba Getir
    //HTTP Yöntemi: GET
    //URL: http://localhost:8080/cars/search?brand=Toyota*/
    @GetMapping("/search")
    public ResponseEntity<List<CarDTO>> getCarsByBrand(@RequestParam(name = "brand") String brand) {
        List<CarDTO> carDTOList = carService.findCarDTOByBrand(brand);
        return ResponseEntity.ok(carDTOList);
    }

    //public List<CarDTO> findCarDTOByBrand(String brand) {
    //    List<Car> carList = carRepository.findCarByBrand(brand);
    //    return carList.stream()
    //          .map(CarDTO::new)
    //          .collect(Collectors.toList());
    //}


    ///7
    //Sayfalandırma İle Arabaları Getir CarDTO ile getirme
    //HTTP Yöntemi: GET
    //URL: http://localhost:8080/cars/s?page=1&size=2&sort=productionYear&direction=ASC
    @GetMapping("/s")
    public ResponseEntity<Page<CarDTO>> getCarDTOPaging(Pageable pageable) {
        Page<CarDTO> carDTOPage = carService.getDTOPage(pageable);
        return ResponseEntity.ok(carDTOPage);
    }

    ///7
    @GetMapping("/c")
    public ResponseEntity<Page<CarDTO>> getCarDTOPaging2(@RequestParam(name = "page") int page,
                                                         @RequestParam(name = "size") int size,
                                                         @RequestParam(name = "sort") String sort,
                                                         @RequestParam (name = "direction")Sort.Direction direction){
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,sort));
        Page<CarDTO> carPage = carService.getDTOPage(pageable);
        return ResponseEntity.ok(carPage);
    }

    ///8
    //DTO Kullanarak Araba Güncelle
    //HTTP Yöntemi: PUT
    //URL: http://localhost:8080/cars/update/2
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCarDto(@PathVariable(name = "id") Long id, @Valid @RequestBody CarDTO carDTO){
        carService.updateCarDTOById(id, carDTO);
        return ResponseEntity.ok("Arac güncellendi!");
    }









}