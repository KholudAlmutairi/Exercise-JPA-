package com.example.capstone1.Service;


import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Repository.MerchantStockRepository;
import com.example.capstone1.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

@RequiredArgsConstructor
public class MerchantStockService {

    private final ProductService productService;
    //private  final ProductRepository productRepository;
    private  final MerchantStockRepository merchantStockRepository;
    //private final MerchantService merchantService;
    //private final MerchantStockService merchantStockService;

    //9- Create endpoint for getting and adding and deleting updating a
    //MerchantStock.


    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public List<MerchantStock> getMerchantStocks() {

        return merchantStockRepository.findAll();
    }


//    public void addMerchantStock(MerchantStock merchantStock) {
//
//        merchantStocks.add(merchantStock);
//    }



//    public void addMerchantStock(MerchantStock merchantStock) {
//        // Check if product id and merchant id are valid
//        if (isValidProductId(merchantStock.getProductid()) && isValidMerchantId(merchantStock.getMerchantid())) {
//            // Add merchant stock if both ids are valid
//            merchantStocks.add(merchantStock);
//        } else {
//            System.out.println("Cannot add merchant stock, invalid product ID or merchant ID.");
//        }
//    }



    public void addMerchantStock(MerchantStock merchantStock) {
          //  merchantStocks.add(merchantStock);}
        merchantStockRepository.save(merchantStock);}





//    public void addMerchantStock(MerchantStock merchantStock, String productid,String merchantid) {
//        if (isProductIdExists(productid)&&isMerchantidExists(merchantid)) {
//            merchantStocks.add(merchantStock);
//
//        } else {
//            throw new IllegalArgumentException("Cannot add the product, the category ID is invalid.");
//        }
//    }



    public Boolean isProductIdExists(String productid) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductid().equalsIgnoreCase(productid)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isMerchantidExists(String merchantid) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getMerchantid().equalsIgnoreCase(merchantid)) {
                return true;
            }
        }
        return false;
    }




//    private boolean isValidProductId(String productId) {
//        return productId != null && !productId.isEmpty();
//    }
//
//    private boolean isValidMerchantId(String merchantId) {
//        return merchantId != null && !merchantId.isEmpty();
//    }



    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {
        MerchantStock m =merchantStockRepository.getById(id);
        if(m==null){
            return  false;
        }

        m.setProductid(merchantStock.getProductid());
        m.setMerchantid(merchantStock.getMerchantid());
        m.setStock(merchantStock.getStock());

        merchantStockRepository.save(m);
        return true;
//        for (int i = 0; i < merchantStocks.size(); i++) {
//            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
//                merchantStocks.set(i, merchantStock);
//                return true;
//            }
//        }
//        return false;
    }


    public boolean deleteMerchantStock(String id) {
        MerchantStock merchantStock=merchantStockRepository.getById(id);
        if(merchantStock==null){
            return false;
        }

        merchantStockRepository.delete(merchantStock);
        return true;

//        for (int i = 0; i < merchantStocks.size(); i++) {
//            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
//                merchantStocks.remove(i);
//                return true;
//            }
//
//        }
//        return false;
    }


    //11- Create endpoint where user can add more stocks of product to a
    //merchant Stock
    //• this endpoint should accept a product id and merchant id and the amount of
    //additional stock.
    ////////////////////////////////////////////////////////////////////////////////
    public boolean reStock(String idproduct, String iduser, String idmerchant, int newstock) {
        if (productService.prodectId(idproduct) == true) {
            for (MerchantStock merchantStock : merchantStocks) {
                for (int i = 0; i < merchantStocks.size(); i++) {
                    if (merchantStocks.get(i).getProductid().equalsIgnoreCase(iduser) &&
                            merchantStocks.get(i).getMerchantid().equalsIgnoreCase(idmerchant)) {
                        merchantStock.setStock(merchantStock.getStock() + newstock);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean reducesStock(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                if(merchantStocks.get(i).getStock()>0)
                    merchantStocks.get(i).setStock(merchantStocks.get(i).getStock()-1);
                return true;
            }
        }
        return false;
    }


   // ترجع اريس لست لكل لجميع البرودكت اللي Stock =0
   //Endpoint 3
    public ArrayList<Product> findLowStock() {
        ArrayList<Product> lowStockList = new ArrayList<>();
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getStock() == 0) {
                for(Product product: productService.products){
                    if(product.getId().equalsIgnoreCase(merchantStock.getId())){
                        lowStockList.add(product);

                    }
                }

            }
        }
        return lowStockList;
    }



 //  بترجع كم مخزون الستك من هذا البرودكت
 //Endpoint 4
    public Integer findLowStock(String productId) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductid().equalsIgnoreCase(productId)) {
                return merchantStock.getStock();
            }
        }
     return 0;
    }


    public boolean addStocksOfProduct(String id, int stock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + stock);
                return true;
            }
        }
        return false;
    }

    public boolean searchIdProduct(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchIdMerchant(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }



}













