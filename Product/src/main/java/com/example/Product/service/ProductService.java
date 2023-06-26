package com.example.Product.service;
import com.example.Product.Customers;
import com.example.Product.OrderProducts;
import com.example.Product.Products;
import com.example.Product.Cart;
import java.util.List;
import java.util.Optional;
public interface ProductService {
     Products productData(Products products); //step(1)->product
     List<Products> getproduct(); //step(2)
    Customers data(Customers customers);      //step(1)->customer
    List<Customers> getCustomerDetails();  //step(2)->customer details
    Optional<Products> getProductId(Integer productId ); //step(3)
     List<Products> getproductCategory(String category);//step(4)
     Cart additemcart(Integer customerId, Integer productId, int quantity);//step(5)
     public List<Cart> getcardId();//step(6)
     Optional<Cart> getcardbyId(Integer cardId);//step(7)
     List<Products> getOnlineProduct(int limit);//step(8)
     List<Cart> limitedDatasfromCart(int limit);//step(9)
     List<Products> getByRateBetween(double minRate, double maxRate);//step(10)
    List<Products> getByCategoryAndRateBetween(String Category, double minRate, double maxRate);//step(11)
    Products updatedata(Integer productId, String regionAvailability, int stock, double rate);//step(12)
    Products updateByPatch(Integer productId, Products products);//step(13)
     List<Products> getProductsSortedByRate(String sort);//step(14)
     List<Products> getProductsSortedByCategory(String sort);//step(15)
     List<Products> getProductsSortedByRatings(String sort);//step(16)
      List<Products> getProductsSortedByName(String sort);//step(17)
      List<Products> getProductsSortedByReviewCount(String sort);//step(18)
    void deletebyid(Integer productId);//step(19)
    double calculateTotalAmount(Integer cartId);//step(20)
    List<Products> getSortedProducts(String sort);

}



