package com.tpe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "lütfen gecerli bir isim giriniz! ")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "lütfen gecerli bir soy isim giriniz ")
    @Column(nullable = false)
    private  String lastName;

    //@Email(message = "lütfen gecerli bir email giriniz!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Lutfen gecerli bir email adresi giriniz")
    @Column(nullable = false,unique = true)
    private String email;


    @Setter(AccessLevel.NONE)
    private LocalDateTime registrationDate;

    @PrePersist
    public void setRegistrationDateAuto(){
        this.registrationDate = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "owner")
    private List<Car> carList = new ArrayList<>();
}
    //`@Email` ve `@Pattern` arasındaki fark şudur:
    //`@Email`: Bu anotasyon, alanın geçerli bir e-posta adresi formatında olup
    //olmadığını kontrol eder. E-posta adresinin geçerli olup olmadığını belirlemek
    //için basit bir kontrol yapar.

    //`@Pattern`: Bu anotasyon, alanın belirli bir düzenli ifade (regex) ile eşleşip
    //eşleşmediğini kontrol eder. Bu, daha karmaşık ve özelleştirilmiş doğrulama
    //kuralları tanımlamak için kullanılır.

    //Örneğin, `@Email` sadece e-posta formatını kontrol ederken, `@Pattern` ile e-posta
    //adresinin belirli karakterler içermesi gerektiğini belirtebilirsiniz.




