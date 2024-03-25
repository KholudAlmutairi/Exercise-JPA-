package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    //7- Create endpoint for getting and adding and deleting updating a
    //Category

    private final CategoryRepository categoryRepository;

    // ArrayList<Category> categories=new ArrayList<>();


    public List<Category> getCategories() {

        return categoryRepository.findAll();
    }


    public void addCategory(Category category) {
        categoryRepository.save(category);
        // categories.add(category);
    }


    public Boolean updateCategory(String id, Category category) {
        Category c = categoryRepository.getById(id);
        if (c == null) {
            return false;
        }
        c.setName(category.getName());
        categoryRepository.save(c);
        return true;
    }

    //for(int i=0; i<categories.size();i++){
    //if(categories.get(i).getId().equalsIgnoreCase(id)){
    //   categories.set(i,category);
    //  return true;
    //}
    //}
    // return false;


    public Boolean deleteCategory(String id) {
        Category category = categoryRepository.getById(id);
        if (category == null) {
            return false;
        }
        categoryRepository.delete(category);
        return true;

//        for (int i=0;i<categories.size();i++){
//            if(categories.get(i).getId().equalsIgnoreCase(id)){
//                categories.remove(i);
//                return true;
//            }
//
//        }
//        return  false;
    }

}





