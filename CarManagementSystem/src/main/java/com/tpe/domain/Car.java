package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Lütfen marka giriniz.")
    @NotNull(message = "Boş gecilemez!!!")
    @Size(min = 2,max = 50,message = "En az 2 karakter girmeniz gerekir")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Lütfen renk giriniz.")
    @NotNull(message = "Boş gecilemez!!!")
    @Size(min = 2,max = 50,message = "En az 2 karakter girmeniz gerekir")
    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    @NotNull(message = "Boş gecilemez!!!")
    private Integer year;

    @ManyToOne
    @JsonIgnore
    private Owner owner;



}
