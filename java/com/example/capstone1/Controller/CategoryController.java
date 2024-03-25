package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/va1/category")
@RequiredArgsConstructor
public class CategoryController {

    //7- Create endpoint for getting and adding and deleting updating a
    //Category

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory(){
        //ArrayList<Category> categories=categoryService.getCategories();
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid Category category , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean idupdate = categoryService.updateCategory(id,category);
        if(idupdate){
            return ResponseEntity.status(200).body(new ApiResponse("Category updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Category not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        Boolean isDeleted= categoryService.deleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Category Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found!"));
    }




}
