package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.MerchantStockService;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    //9- Create endpoint for getting and adding and deleting updating a
    //MerchantStock.


    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
       // ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

//    @PostMapping("/add")
//    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
//        if(errors.hasErrors()){
//            String message=errors.getFieldError().getDefaultMessage();
//            ResponseEntity.status(400).body(message);
//        }
//        merchantStockService.addMerchantStock(merchantStock);
//        return ResponseEntity.status(200).body(new ApiResponse("MerchantStock added"));
//
//    }




  @PostMapping("/add")
   public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
      if(errors.hasErrors()){
       String message = errors.getFieldError().getDefaultMessage();
      return ResponseEntity.status(400).body(message);
       }
      merchantStockService.addMerchantStock(merchantStock);
      return ResponseEntity.status(200).body(new ApiResponse("MerchantStock added"));
    }




//    @PostMapping("/add")
//    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
//        if(errors.hasErrors()){
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//        }
//        String productid = merchantStock.getProductid();
//        String merchantid = merchantStock.getProductid();
//
//
//        if (merchantStockService.isProductIdExists(productid)&& merchantStockService.isMerchantidExists(merchantid)) {
//            return ResponseEntity.status(400).body("Cannot add the product, the specified category does not exist.");
//        }
//
//        merchantStockService.addMerchantStock(merchantStock,productid,merchantid);
//        return ResponseEntity.status(200).body(new ApiResponse("MerchantStock added"));
//    }



    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        Boolean idupdate = merchantStockService.updateMerchantStock(id, merchantStock);
        if(idupdate){
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("MerchantStock not found"));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id){
        Boolean isDeleted= merchantStockService.deleteMerchantStock(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("MerchantStock not found!"));
    }

    //11- Create endpoint where user can add more stocks of product to a merchant Stock
   //• this endpoint should accept a product id and merchant id and the amount of additional stock.

    @GetMapping("/restock/{idproduct}/{iduser}/{idmerchant}/{newstock}")
    public ResponseEntity reStock(@PathVariable String idproduct,@PathVariable String iduser,
                                  @PathVariable String idmerchant,@PathVariable int newstock){
        if(merchantStockService.reStock(idproduct,iduser,idmerchant,newstock)==false){
            return ResponseEntity.status(400).body("Invalid ID");
        }
        return ResponseEntity.status(200).body("Done");
    }



//    @GetMapping("/lowStock/{productId}")
//    public ResponseEntity findLowStock(@PathVariable String productId) {
//        ArrayList<MerchantStock> lowStockList = merchantStockService.findLowStock(productId);
//        if (lowStockList.isEmpty()) {
//            return ResponseEntity.status(400).body("No low stock found for product with ID: " + productId);
//        }
//        return ResponseEntity.ok(lowStockList);
//    }

    @GetMapping("/lowStock")
    public ResponseEntity findLowStock() {
        ArrayList<Product> lowStockList = merchantStockService.findLowStock();
        if (lowStockList.isEmpty()) {
            return ResponseEntity.status(400).body("No low stock found ");
        }
        return ResponseEntity.ok(lowStockList);
    }

    @GetMapping("/lowStock/{productId}")
    public ResponseEntity getProductStock(@PathVariable String productId) {
        Integer stock = merchantStockService.findLowStock(productId);
        if (stock != null) {
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.status(400).body(null);
            //return ResponseEntity.status(400).body("No low stock found for product with ID: ");
        }
    }





//
//    @PostMapping("/addStock/{id}/{stock}")
//    public ResponseEntity addStocksOfProduct(@PathVariable String id ,@PathVariable int stock){
//        boolean isAddStockesOfProduct =merchantStockService.addStocksOfProduct(id,stock);
//        if(isAddStockesOfProduct){
//            return ResponseEntity.status(200).body(new ApiResponse("Done add stocks of product to a merchant Stock "));
//        }
//
//        return ResponseEntity.status(400).body(new ApiResponse("Not found add stocks of product to a merchant stock"));
//
//    }

}
