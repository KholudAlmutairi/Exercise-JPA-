package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

//    private final ProductService productService;
//    private final MerchantService merchantService;
//    private final MerchantStockService merchantStockService;
   // private  final  CategoryService categoryService;
      private final MerchantRepository merchantRepository;
    //8- Create endpoint for getting and adding and deleting updating a
    //Merchant
   ArrayList<Merchant> merchants=new ArrayList<>();



    public List<Merchant> getMerchants(){

        return merchantRepository.findAll();
    }


    public void addMerchant(Merchant merchant){
         merchantRepository.save(merchant);
        //merchants.add(merchant);
    }

    public Boolean updateMerchant(String id,Merchant merchant){
        Merchant m=merchantRepository.getById(id);
        if(m==null){
            return false;
        }
        m.setName(merchant.getName());


        merchantRepository.save(m);

        return true;



//        for(int i=0; i<merchants.size();i++){
//            if(merchants.get(i).getId().equalsIgnoreCase(id)){
//                merchants.set(i,merchant);
//                return true;
//            }
//        }
//        return false;
    }


    public Boolean deleteMerchant(String id){
       Merchant merchant=merchantRepository.getById(id);
       if(merchant==null){
           return false;
       }

       merchantRepository.delete(merchant);
       return true;


//        for (int i=0;i<merchants.size();i++){
//            if(merchants.get(i).getId().equalsIgnoreCase(id)){
//                merchants.remove(i);
//                return true;
//            }
//
//        }
//        return  false;
    }


    public Merchant getMerchantById(String id) {
        for (Merchant merchant : merchants) {
            if (merchant.getId().equalsIgnoreCase(id)){
                return merchant;
            }
        }
        return null;
    }


}







