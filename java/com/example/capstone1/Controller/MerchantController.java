package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.CategoryService;
import com.example.capstone1.Service.MerchantService;
import com.example.capstone1.Model.MerchantStock;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    //8- Create endpoint for getting and adding and deleting updating a
    //Merchant

    private final MerchantService merchantService;
    private final CategoryService categoryService;


    @GetMapping("/get")
    public ResponseEntity getMerchant(){
       // ArrayList<Merchant> merchants= merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }
//
    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
        }
        Boolean idupdate = merchantService.updateMerchant(id,merchant);
        if(idupdate){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        Boolean isDeleted= merchantService.deleteMerchant(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found!"));
    }



    @GetMapping("/get/{id}")
    public ResponseEntity getMerchantById(@PathVariable String id) {
        Merchant merchant = merchantService.getMerchantById(id);
        if (merchant == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found"));
        }
        return ResponseEntity.status(200).body(merchant);
    }


}








