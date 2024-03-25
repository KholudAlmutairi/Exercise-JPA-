package com.example.capstone1.Service;

import com.example.capstone1.Model.User;
import com.example.capstone1.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

@RequiredArgsConstructor
public class UserService {
    private final ProductService productService;
    private final MerchantStockService merchantStockService;
    private  final UserRepository userRepository;


    //10- Create endpoint for getting and adding and deleting updating a User

    ArrayList<User> users = new ArrayList<>();

    public List<User> getUsers() {

        return userRepository.findAll();
    }


    public void addUser(User user) {

        userRepository.save(user);
    }


    public Boolean updateUser(String id, User user) {
      User u=userRepository.getById(id);
      if(u==null){
          return false;
      }

      u.setUsername(u.getUsername());
      u.setPassword(u.getPassword());
      u.setEmail(user.getEmail());
      u.setRole(u.getRole());
      u.setBalance(user.getBalance());
      userRepository.save(u);
      return true;

    }


    public Boolean deleteUser(String id) {
      User user=userRepository.getById(id);
      if(user==null){
          return false;
      }
      userRepository.delete(user);
      return true;
    }


    //12- Create endpoint where user can buy a product directly
    //• this endpoint should accept user id, product id, merchant id.
    //• check if all the given ids are valid or not
    //• check if the merchant has the product in stock or return bad request.
    //• reduce the stock from the MerchantStock.
    //• deducted the price of the product from the user balance.
    //• if balance is less than the product price return bad request.

    public Boolean buyProduct(String userId, String productId, String merchantId) {
        double price = 0;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                if (merchantStockService.searchIdMerchant(merchantId)) {
                    if (merchantStockService.searchIdProduct(productId)) {
                        if(merchantStockService.findLowStock(productId)>0)
                        if (merchantStockService.reducesStock(productId)) {
                            price = productService.getPriceOfProduct(productId);
                            if (price > 0) {
                                user.setBalance(user.getBalance() - price);
                                return true;
                            }
                        }
                    }

                }
            }
        }


        return false;
    }

}










