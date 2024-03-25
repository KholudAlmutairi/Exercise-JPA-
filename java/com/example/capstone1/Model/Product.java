
package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 4,message = "Name must be more than 3 characters long")
    //@Column(columnDefinition = "varchar(20) check(length(name)>3) not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "price must not be empty")
    @Positive(message = "price must be a positive number")
    @Column(columnDefinition = "double not null")
    private Double  price;

    @NotEmpty(message = "categoryID must not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String categoryID;
}



//////////////
//package com.example.capstone1.Model;

//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@Entity
//@NoArgsConstructor
//public class Product {
////1- Create Product Class:
////            • id (must not be empty).
////            • name (must not be empty, have to be more than 3 length long).
////            • price (must not be empty, must be positive number).
////            • categoryID (must not be empty).
//
//
////
//    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE)
//    private String id;
//
//    @NotEmpty(message = "name must not be empty")
//    @Size(min = 4,message = " Name have to be more than 3 length long")
//    @Column(columnDefinition = "varchar(20) not null")
//    private String name;
//
//
//    @NotNull(message = "must not be empty")
//    @Positive(message = "must be positive number")
//    @Column(columnDefinition = "double not null")
//    private Double  price ;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    //@NotEmpty(message ="categoryID must not be empty")
//    //@NotBlank(message = "Category ID is required")
//    private String categoryID;
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
