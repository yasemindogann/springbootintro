package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

//@Data   //Hepsinin(asagıdaki dortlu) yerine kullanabilirim
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor  //parametre olarak üretilmesini eklenmesini istediğimiz field'a final ekliyoruz.

@Entity
public class Student {

    @Id //Entity classlara eklemek zorundayım.PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Otomatik id oluşturulacak
    //@Getter //id sadece get'leniyor set'lenmiyor.Ama böyle bütün field'lara tek tek yazmam lazım @Getter, @Setter diye
    //ya da
    //@Setter(AccessLevel.NONE)   //Bütün fieldların getter setter'ıçalışacak sadece id'nin getter'ı çalışacak setter'ı çalışmayacak
    // final yazmadığım için constructor üretmiyor.
    private Long id;

    //Validasyonu @Validation ile ilk olarak controller'da yapar.
    //Daha sonra Repo'da yapar.(Çift dikiş)
    //Controller'ı yazan da Repo'yu yazan da birbirinden haberi olmadığı için ikisi de bu kontrolü yapar.
    @NotNull(message = "First name can not be null")//Bu kontroller Controller'da yapılır.
    @NotBlank(message = "First name can not be white space")//Bu kontroller Controller'da yapılır.
    @Size(min = 2, max = 25, message = "First name '${validatedValue}' must be between {min} and {max} long")//Bu kontroller Controller'da yapılır.
    @Column(nullable = false, length = 25)  //Bu kontrol Repository'de yapılır.
    private /*final*/ String name;
    //'${validatedValue}'  --> Kullanıcı adı dinamik gelsin diye.

    @NotNull(message = "First lastname can not be null")//Bu kontroller Controller'da yapılır.
    @NotBlank(message = "First lastname can not be white space")//Bu kontroller Controller'da yapılır.
    @Size(min = 2, max = 25, message = "First lastname '${validatedValue}' must be between {min} and {max} long")//Bu kontroller Controller'da yapılır.
    @Column(nullable = false, length = 25)  //Bu kontrol Repository'de yapılır.
    private /*final*/ String lastName;

    private int grade;

    @Column(nullable = false, unique = true)
    //"@." geçerli mi diye kontrol ediyor.("@" var mi, "." var mi ve noktadan sonra com,org.. bir şey geliyor mu bunları kontrol ediyor.(contains() methodu ile yapıyor bu kontrolü)
    @Email(message = "Provide valid email")
    private /*final*/ String email;

    private /*final*/ String phoneNumber;

    // final yazmadığım için constructor üretmiyor.
    //@Setter(AccessLevel.NONE) //Burası da setlenmesin
    private LocalDateTime createDate = LocalDateTime.now();

}

/*
   public Student(String name, String lastName, int grade, String email, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    Yukardaki kodun oluşmasını istiyorsam;

    Yukarda yer alan field'lara final eklemem lazım.
    id ve createDate constructor içinde yer almasın diye onlara final eklemedim.

    *****************
    !!bu classtan bi obje üretileceği zaman final variable olduğundan dolayı bizi parametreli cons. kullanmaya zorluyor
*/

