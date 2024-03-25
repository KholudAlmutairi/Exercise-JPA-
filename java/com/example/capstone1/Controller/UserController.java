package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.MerchantStockService;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    //10- Create endpoint for getting and adding and deleting updating a User

    private final UserService userService;
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        //ArrayList<User> users=userService.getUsers();
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
        }
        Boolean idupdate =userService.updateUser(id,user);

        if(idupdate){
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("User not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        Boolean isDeleted= userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found!"));
    }


//    12- Create endpoint where user can buy a product directly
//• this endpoint should accept user id, product id, merchant id.
//• check if all the given ids are valid or not
//• check if the merchant has the product in stock or return bad request.
//            • reduce the stock from the MerchantStock.
//            • deducted the price of the product from the user balance.
//            • if balance is less than the product price return bad request.


//
//    @PostMapping("/buyProduct")
//    public ResponseEntity buyProduct(@RequestParam String userId, @RequestParam String productId, @RequestParam String merchantId) {
//        boolean isBought = userService.buyProduct(userId, productId, merchantId);
//
//        if (isBought) {
//            return ResponseEntity.status(200).body(new ApiResponse("Product bought successfully"));
//        }
//
//        return ResponseEntity.status(400).body(new ApiResponse("Failed to buy product"));
//    }

    //12
    @PostMapping("/buyProduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId) {

        boolean isBought = userService.buyProduct(userId, productId, merchantId);

        if (isBought) {
            return ResponseEntity.status(200).body(new ApiResponse("Product bought successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Failed to buy product"));
    }









}

