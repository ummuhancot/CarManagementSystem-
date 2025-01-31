package com.tpe.controller;

import com.tpe.dto.OwnerDTO;
import com.tpe.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    ///1
    //HTTP Yöntemi: POST
    //URL: http://localhost:8080/owners/save
    //Gönderim Formatı: JSON*/
    @PostMapping("/save")
    public ResponseEntity<String> createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
        ownerService.saveOwner(ownerDTO);
        return ResponseEntity.ok("Sahip kaydedildi!");
    }

    ///2
    //Tüm Sahipleri Bul
    //  HTTP Yöntemi: GET
    //  URL: http://localhost:8080/owners
    @GetMapping
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        List<OwnerDTO> ownerDTOList = ownerService.getAllOwnersDTO();
        return ResponseEntity.ok(ownerDTOList);
    }

    /// 3
    //ID ile Sahip Bul
    //HTTP Yöntemi: GET
    //URL: http://localhost:8080/owners/2
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> findOwnerDTOById(@PathVariable Long id){
        OwnerDTO ownerDTO = ownerService.getOwnerDTOById(id);
        return ResponseEntity.ok(ownerDTO);
    }

    ///4--->
    //*Bir Sahibe Araba Ekle
    //HTTP Yöntemi: POST
    //URL: http://localhost:8080/owners/add?car=3&owner=1
    @PatchMapping("/add")
    public ResponseEntity<String> getCarToOwnerById(@RequestParam(name = "car") Long carId,@RequestParam(name = "owner") Long ownerId){
        ownerService.addCarToOwner(carId,ownerId);
        return ResponseEntity.ok(carId+"Id ye sahip arac başarıyla"+ ownerId + "id li kişiye eklendi");

    }

    ///5--->
    //*Araba Satışı Yap
    //HTTP Yöntemi: POST
    //URL: http://localhost:8080/owners/sell?car=3&buyer=2*/
    @PatchMapping("/sell")
    public ResponseEntity<String> sellCarFromOwnerById(@RequestParam(name = "car") Long carId, @RequestParam(name = "owner") Long ownerId){
        ownerService.sellCarFromOwner(carId,ownerId);
        return ResponseEntity.ok(ownerId +  "id li kisinin " + carId + " id li arac satisi gerceklesti." );
    }


}
