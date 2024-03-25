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
public class MerchantStock {
//    4- Create MerchantStock Class:
//            • id (must not be empty).
//            • productid (must not be empty).
//            • merchantid (must not be empty).
//            • stock (must not be empty, have to be more than 10 at start)

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private String id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    //    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
//    private Merchant merchant;
//
//    //@NotEmpty(message = "id productid must not be empty ")
       @Column(columnDefinition = "varchar(10) not null ")
       private String productid;
//
//    //@NotEmpty(message = "merchant id must not be empty ")

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
      @Column(columnDefinition = " varchar(10)not null ")
      private String merchantid ;

      //@NotNull(message = "name must not be empty")
      //@Pattern(regexp = "")//Stock have to be more than 10 length long


//      @NotNull(message = "Stock must not be empty")
//      @Min(value = 11, message = "Stock must be more than 10")
//      @Column(columnDefinition = "int not null")
//      private Integer stock=10;

      @Size(min = 10,message = " Stock have to be more than 10 length long")
      @Min(10)
      @Column(columnDefinition = "int default 10 not null")
      private Integer stock;


      //@Size(min = 10,message = " Stock have to be more than 10 length long")
      //@Min(10)
      //private int stock=10;











}
