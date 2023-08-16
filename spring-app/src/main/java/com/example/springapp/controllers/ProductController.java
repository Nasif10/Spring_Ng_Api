package com.example.springapp.controllers;

import com.example.springapp.entities.Product;
import com.example.springapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/getProducts")
    public List<Product> getProducts()
    {
        return productService.getProducts();
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value="id") int id)
    {
        try {
            Optional<Product> product = productService.getProductById(id);
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getProductsByCategory")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(value = "category") String category){

        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (!products.isEmpty()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        try {
            Product addProduct = productService.addProduct(product);
            return new ResponseEntity<>(addProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products)
    {
        return productService.addProducts(products);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") int id, @RequestBody Product product)
    {
        Product updatedProduct = productService.updateProduct(id, product);
        try {
            if (updatedProduct != null) {
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value="id") int id)
    {
        try {
            boolean deleted = productService.deleteProduct(id);
            if (deleted) {
                return new ResponseEntity<>("Product with Id " + id + " deleted.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product with Id " + id + " not found.", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
