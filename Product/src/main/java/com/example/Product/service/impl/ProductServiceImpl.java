package com.example.Product.service.impl;
import com.example.Product.Customers;
import com.example.Product.OrderProducts;
import com.example.Product.Products;
import com.example.Product.Cart;
import com.example.Product.exception.ExceptionError;
import com.example.Product.repoistry.CustomersRepository;
import com.example.Product.repoistry.OrderServiceRepoistry;
import com.example.Product.repoistry.ProductsRepoistry;
import com.example.Product.repoistry.CartsRepository;
import com.example.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductsRepoistry productrepoistry;
    @Autowired
    CartsRepository productrepoistry1;
    @Autowired
    CustomersRepository customersRepository;
    @Autowired
    OrderServiceRepoistry orderServiceRepoistry;
    @Autowired
    SmsserivesClass smsserivesClass;
    @Override//step(1)
    public Products productData(Products products) {
        return productrepoistry.save(products);
    }
    @Override//step(2)
    public List<Products> getproduct() {
        Optional<List<Products>> products1;
        products1 = Optional.ofNullable(productrepoistry.findAll());
        if (products1.get().isEmpty()) {
            throw new ExceptionError("Give produts is empty.Add some data to products");
        }
        return products1.get();
    }
    @Override //step(1)->customer
    public Customers data(Customers customers) {
        return customersRepository.save(customers);
    }
    @Override //step(1)->customerdetails
    public List<Customers> getCustomerDetails() {
        Optional<List<Customers>> customers1;
        customers1 = Optional.ofNullable(customersRepository.findAll());
        if (customers1.get().isEmpty()) {
            throw new ExceptionError("Give produts is empty.Add customer details");
        }
        return customers1.get();
    }
    @Override//step(3)
    public Optional<Products> getProductId(Integer productId) {
        return Optional.ofNullable((productrepoistry.findById(productId).orElseThrow(() -> new ExceptionError("given product id does not exist"))));
    }
    @Override//step(4)
    public List<Products> getproductCategory(String category) {
        Optional<List<Products>> category1;
        category1 = Optional.ofNullable(productrepoistry.findByCategory(category));
        if (category1.get().isEmpty()) {
            throw new ExceptionError("Given Categories Does Not Exits : " + category);
        }
        return category1.get();
    }
    @Override //step(5)
    public Cart additemcart(Integer customerId, Integer productId, int quantity) {
        Optional<Products> addProduct = Optional.ofNullable(productrepoistry.findById(productId).orElseThrow(() -> new ExceptionError("Given productId does not exist")));
       Optional <Customers> existingCustomer = Optional.ofNullable(customersRepository.findById(customerId).orElseThrow(() -> new ExceptionError("Given customerId does not exist")));
        Cart cart = new Cart();
        cart.setCustomers(existingCustomer.get());
        cart.setProducts(addProduct.get());
        cart.setQuantity(quantity);
        cart.setCartId(customerId);
        return productrepoistry1.save(cart);
    }
    @Override //step(6)
    public List<Cart> getcardId() {
        Optional<List<Cart>> carts = Optional.ofNullable(productrepoistry1.findAll());
        if (carts.get().isEmpty()) {
            throw new ExceptionError("Your cart is empty.Add some products to the cart");
        }
        return carts.get();
    }
    @Override  //step(7)
    public Optional<Cart> getcardbyId(Integer cardId) {
        return Optional.ofNullable(productrepoistry1.findById(cardId).orElseThrow(() -> new ExceptionError("Given cardId was mismatched")));
    }
    @Override//(step 8)
    public List<Products> getOnlineProduct(int limit) {
        Optional<List<Products>> limitedproducts = Optional.of(productrepoistry.findAll().stream().limit(limit).collect(Collectors.toList()));
        if (limitedproducts.get().isEmpty()) {
            throw new ExceptionError("Given Limit Does Not Exits in products");
        }
        return limitedproducts.get();
    }
    @Override //(step 9)
    public List<Cart> limitedDatasfromCart(int limit) {
        Optional<List<Cart>> limitedCartData = Optional.of(productrepoistry1.findAll().stream().limit(limit).collect(Collectors.toList()));
        if (limitedCartData.get().isEmpty()) {
            throw new ExceptionError("Given Limit Does Not Exits in Carts");
        }
        return limitedCartData.get();
    }
    @Override //(step 10)
    public List<Products> getByRateBetween(double minRate, double maxRate) {
        Optional<List<Products>> getProductRange;
        getProductRange = Optional.of(productrepoistry.findByRateBetween(minRate, maxRate).stream().
                filter(products -> products.getRate() >= minRate && products.getRate() <= maxRate).collect(Collectors.toList()));
        if (getProductRange.get().isEmpty()) {
            throw new ExceptionError("The given MaximumRate and MinimumRate does not exists in ProductRate ");
        }
        return getProductRange.get();
    }
    @Override //(step 11)
    public List<Products> getByCategoryAndRateBetween(String Category, double minRate, double maxRate) {
        Optional<List<Products>> getCategoryRange;
        getCategoryRange = Optional.ofNullable(productrepoistry.findByCategory(Category).stream().filter(products -> products.getRate()>=minRate&& products.getRate()<=maxRate).collect(Collectors.toList()));
        if (getCategoryRange.get().isEmpty()) {
            throw new ExceptionError("The given MaximumRate and MinimumRate does not exists in CategoryRate ");
        }
        return getCategoryRange.get();
    }
    @Override //(step 12)
    public Products updatedata(Integer productId, String regionAvailability, int stock, double rate) {
        Optional<Products> online = Optional.ofNullable(productrepoistry.findById(productId).orElseThrow(() -> new ExceptionError("Given product is does not exist")));
        Products products1 = new Products();
        if (online.isPresent()) {
            products1 = online.get();
            products1.setRegionAvailability(regionAvailability);
            products1.setStock(stock);
            products1.setRate(rate);
        }
        return productrepoistry.save(products1);
    }
    @Override//step(13)
    public Products updateByPatch(Integer productId, Products products) {
        Optional<Products> online = Optional.ofNullable(productrepoistry.findById(productId).orElseThrow(() -> new ExceptionError("Given Product ID Dose Not Exits :")));
        Products products1 = new Products();
        if (online.isPresent()) {
            products1 = online.get();
            products1.setRegionAvailability(products.getRegionAvailability());
            products1.setStock(products.getStock());
            products1.setRate(products.getRate());
            products1.setRatings(products.getRatings());
            products1.setReviewCount(products.getReviewCount());
        }
        return productrepoistry.save(products1);
    }
    @Override//step(14)
    public List<Products> getProductsSortedByRate(String sort) {
        Optional<List<Products>> Mylist = Optional.of(productrepoistry.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getRate))
                        .collect(Collectors.toList());
                return answer;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getRate).reversed())
                        .collect(Collectors.toList());
                return answer;
            }
        }
        throw new ExceptionError("Method Not Found");
    }
    @Override//step(15)
    public List<Products> getProductsSortedByCategory(String sort) {
        Optional<List<Products>> Mylist = Optional.of(productrepoistry.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getCategory))
                        .collect(Collectors.toList());
                return answer;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getCategory).reversed())
                        .collect(Collectors.toList());
                return answer;
            }
        }
        throw new ExceptionError("Method Not Found");
    }
    @Override//step(16)
    public List<Products> getProductsSortedByRatings(String sort) {
        Optional<List<Products>> Mylist = Optional.of(productrepoistry.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getRatings))
                        .collect(Collectors.toList());
                return answer;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getRatings).reversed())
                        .collect(Collectors.toList());
                return answer;
            }
        }
        throw new ExceptionError("Method Not Found");
    }
    @Override//step(17)
    public List<Products> getProductsSortedByName(String sort) {
        Optional<List<Products>> Mylist = Optional.of(productrepoistry.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getName))
                        .collect(Collectors.toList());
                return answer;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getName).reversed())
                        .collect(Collectors.toList());
                return answer;
            }
        }
        throw new ExceptionError("Method Not Found");
    }
    @Override//step(18)
    public List<Products> getProductsSortedByReviewCount(String sort) {
        Optional<List<Products>> Mylist = Optional.of(productrepoistry.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getReviewCount))
                        .collect(Collectors.toList());
                return answer;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getReviewCount).reversed())
                        .collect(Collectors.toList());
                return answer;
            }
        }
        throw new ExceptionError("Method Not Found");
    }
    @Override//step(19)
    public void deletebyid(Integer productId) {
        Optional<Products> op = Optional.ofNullable(productrepoistry.findById(productId).orElseThrow(() -> new ExceptionError("Your productID is not exist")));  //(step 19)
        if (op.isPresent()) {
            productrepoistry.deleteById(productId);
        }
    }
    @Override//step(20)
    public double calculateTotalAmount(Integer cartId) {
        Optional<List<Cart>> carts = Optional.ofNullable(productrepoistry1.findByCartId(cartId));
        double originalAmount = 0.0;
        for (Cart cart : carts.get()) {//apply strems to get each product in customer cart
            originalAmount = carts.get().stream().mapToDouble(c -> c.getProducts().getRate() * c.getQuantity()).sum();
        }
        double gst = originalAmount * 14 / 100;
        double deliveryCharge = 10.0;
        double discount = 5.0 / originalAmount * 100;
        double totalAmount = Math.ceil(originalAmount + gst + deliveryCharge - discount);
        for (Cart cart1 : carts.get()) {
            OrderProducts order=new OrderProducts();
            order.setQuantity(cart1.getQuantity());
            order.setTotalamount(totalAmount);
            order.setOrderDate(LocalDate.now());
            order.setDeliveryDate(LocalDate.now().plusDays(7));
            order.setProductname(cart1.getProducts().getName());
            order.setCartId(cart1.getCartId());
            orderServiceRepoistry.save(order);
            Optional<Products> product = Optional.ofNullable(productrepoistry.findById(cart1.getProducts().getProductId())
                    .orElseThrow(() -> new ExceptionError("Product not found")));
            int purchasedQuantity = cart1.getQuantity();
            int currentStock = productrepoistry.findById(cart1.getProducts().getProductId()).get().getStock();
            int newStock = currentStock - purchasedQuantity;
            if (carts.isPresent()) {
                smsserivesClass.sendSms("+916382200924", "Dear Customer,congrats!..Your order is on the way....ThankYou");
            } else{
                    smsserivesClass.sendSms("+916382200924", "Dear Customer,congrats!..Your order is OUT-OF-STOCK....ThankYou");
                }
            product.get().setStock(newStock);
            productrepoistry.save(product.get());
            productrepoistry1.delete(cart1);
        }
        return totalAmount;
    }
    @Override
    public List<Products> getSortedProducts(String sort) {
        Optional<List<Products>> Mylist = Optional.of(productrepoistry.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getName).thenComparing(Products::getRatings))
                        .collect(Collectors.toList());
                return answer;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (Mylist.isPresent()) {
                List<Products> sortedTable = Mylist.get();
                List<Products> answer = sortedTable.stream()
                        .sorted(Comparator.comparing(Products::getName).thenComparing(Products::getRatings).reversed())
                        .collect(Collectors.toList());
                return answer;
            }
        }
        throw new ExceptionError("Method Not Found");
    }
}

