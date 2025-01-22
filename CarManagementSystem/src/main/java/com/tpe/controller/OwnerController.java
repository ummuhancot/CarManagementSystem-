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
    public ResponseEntity<String> createOwner(@Valid @RequestBody OwnerDTO ownerDTO){
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


}
