package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {
//
//2- Create Category Class: •
//    id (must not be empty).
//            • name (must not be empty, have to be more than 3 length long)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 4,message = " Name have to be more than 3 length long")
    //@Column(columnDefinition = "varchar(20) check(length(name)>3) not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;






}
