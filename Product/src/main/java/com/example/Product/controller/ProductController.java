package com.example.Product.controller;
import com.example.Product.*;
import com.example.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/user")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/productData")
    //http://localhost:8080/user/productData (step1)
    public ResponseEntity<Products> addproduct(@RequestBody Products products) {
        return new ResponseEntity<Products>(productService.productData(products), HttpStatus.ACCEPTED);
    }
    @GetMapping("/Data")
    //http://localhost:8080/user/Data  (step2)
    public ResponseEntity<List<Products>> productid() {
        return new ResponseEntity<List<Products>>(productService.getproduct(), HttpStatus.ACCEPTED);
    }
    @PostMapping("/CustomerBio")
    //http://localhost:8080/user/CustomerBio (step 1)->customer
    public ResponseEntity<Customers> addcustomerproduct(@RequestBody Customers customers) {
        return new ResponseEntity<Customers>(productService.data(customers), HttpStatus.ACCEPTED);
    }
    @GetMapping("/Customerdetails")
    //http://localhost:8080/user/Customerdetails  (step2)->customer
    public ResponseEntity<List<Customers>> customerid() {
        return new ResponseEntity<List<Customers>>(productService.getCustomerDetails(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/getproductByid/{productId}")
    //http://localhost:8080/user/getproductByid/5 (step3)
    public ResponseEntity<Optional<Products>> getproductbyid(@PathVariable Integer productId) {
        return new ResponseEntity<Optional<Products>>(productService.getProductId(productId), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{category}")
    //http://localhost:8080/user/Bags (step4)
    public List<Products> getproductCategory(@PathVariable String category) {
        return productService.getproductCategory(category);
    }
    @PostMapping("/add/{customerId}/{productId}/{quantity}")
    //http://localhost:8080/user/add/1/1/5 (step5)
    public Cart additemcart(@PathVariable Integer customerId, @PathVariable Integer productId, @PathVariable int quantity) {
        return productService.additemcart(customerId, productId, quantity);
    }
    @GetMapping("/readcart")
    //http://localhost:8080/user/readcart (step6)
    public ResponseEntity<List<Cart>> cardId() {
        return new ResponseEntity<List<Cart>>(productService.getcardId(), HttpStatus.ACCEPTED);

    }
    @GetMapping("/getcartbyid/{cardId}")
    //http://localhost:8080/user/getcartbyid/31(step7)
    public ResponseEntity<Optional<Cart>> getcartbyid(@PathVariable Integer cardId) {
        return new ResponseEntity<Optional<Cart>>(productService.getcardbyId(cardId), HttpStatus.ACCEPTED);  //ok
    }
    @GetMapping("/Products")
    //http://localhost:8080/user/Products?limit=2 (step8)
    public ResponseEntity<List<Products>> getlimitedDetails(@RequestParam("limit") int limit) {
        return new ResponseEntity<List<Products>>(productService.getOnlineProduct(limit), HttpStatus.ACCEPTED);
    }
    @GetMapping("/Carts")
    //http://localhost:8080/user/Carts?limit=2 (step9)
    public ResponseEntity<List<Cart>> limitedDatasfromCart(@RequestParam("limit") int limit) {
        return new ResponseEntity<List<Cart>>(productService.limitedDatasfromCart(limit), HttpStatus.ACCEPTED);
    }
    @GetMapping("/Productrange")
    //http://localhost:8080/user/Productrange?minRate=1000&maxRate=3000  (step 10)
    public ResponseEntity<List<Products>> getByRateBetween(
            @RequestParam("minRate") double minRate,
            @RequestParam("maxRate") double maxRate) {
        List<Products> productsList = productService.getByRateBetween(minRate, maxRate);
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }
    @GetMapping("/Categoryrange")
    //http://localhost:8080/user/Categoryrange?Category=Bags&minRate=100&maxRate=300 (step 11)
    public List<Products> getByCategoryAndRateBetween(
            @RequestParam String Category,
            @RequestParam double minRate,
            @RequestParam double maxRate) {
        return productService.getByCategoryAndRateBetween(Category, minRate, maxRate);
    }
    @PutMapping("/updatedata/{productId}/{regionAvailability}/{stock}/{rate}")
    //http://localhost:8080/user/updatedata/13/yes/12/3000 (step 12)
    public Products updateBookById(@PathVariable Integer productId, @PathVariable String regionAvailability, @PathVariable int stock, @PathVariable double rate) {
        return productService.updatedata(productId, regionAvailability, stock, rate);
    }
    @PatchMapping("/patchByUpdate/{productId}")
    //http://localhost:8080/user/patchByUpdate/2 (step 13)
    public ResponseEntity<String> updateByPatch(@PathVariable Integer productId, @RequestBody Products products) {
        productService.updateByPatch(productId, products);
        return ResponseEntity.ok("Product details updated successfully");
    }
    @GetMapping("/SortUsingRate/{sort}")
    //http://localhost:8080/user/SortUsingRate/desc (step 14)
    public List<Products> getProductsSortedByRate(@PathVariable String sort) {
        return productService.getProductsSortedByRate(sort);
    }
    @GetMapping("/SortUsingCategory/{sort}")
    //http://localhost:8080/user/SortUsingCategory/desc (step 15)
    public List<Products> getProductsSortedCategory(@PathVariable String sort) {
        return productService.getProductsSortedByCategory(sort);
    }
    @GetMapping("/SortUsingRatings/{sort}")
    //http://localhost:8080/user/SortUsingRatings/desc (step 16)
    public List<Products> getProductsSortedByRatings(@PathVariable String sort) {
        return productService.getProductsSortedByRatings(sort);
    }
    @GetMapping("/SortUsingName/{sort}")
    //http://localhost:8080/user/SortUsingName/desc (step 17)
    public List<Products> getProductsSortedByName(@PathVariable String sort) {
        return productService.getProductsSortedByName(sort);
    }
    @GetMapping("/SortUsingReviewCount/{sort}")
    //http://localhost:8080/user/SortUsingReviewCount/desc (step 18)
    public List<Products> getProductsSortedReviewCount(@PathVariable String sort) {
        return productService.getProductsSortedByReviewCount(sort);
    }
    @DeleteMapping("/deleteProduct/{productId}")
    //http://localhost:8080/user/deleteProduct/26 (step 19)
    public ResponseEntity<String> deleteid(@PathVariable Integer productId) {
        productService.deletebyid(productId);
        return ResponseEntity.ok("Product details deleted successfully");
    }
    @GetMapping("/{cartId}/total-amount")
    //http://localhost:8080/user/1/total-amount (step 20)
    public ResponseEntity<Double> calculateTotalAmount(@PathVariable Integer cartId) {
        return new ResponseEntity<Double>(productService.calculateTotalAmount(cartId), HttpStatus.OK);
    }

    @GetMapping("/SortProducts/{sort}")
    //http://localhost:8080/user/SortProducts/desc (step 21)
    public List<Products>  getSortedProducts(@PathVariable String sort) {
        return productService.getSortedProducts(sort);
    }
}