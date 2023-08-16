package com.example.springapp.services;

import com.example.springapp.entities.Product;
import com.example.springapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id)
    {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByCategory(String category)
    {
        return productRepository.findByCategory(category);
    }

    public Product addProduct(Product product)
    {
        return productRepository.save(product);
    }

    public List<Product> addProducts(List<Product> products)
    {
        return productRepository.saveAll(products);
    }

    public Product updateProduct(int id, Product product)
    {
        if(!productRepository.existsById(id)){
            return null;
        }
        product.setId(id);
        return productRepository.save(product);
    }

    public boolean deleteProduct(int id)
    {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else
            return false;
    }

}
