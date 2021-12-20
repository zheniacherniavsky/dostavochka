package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.Account;
import com.pet.dostavochka.Model.Product;
import com.pet.dostavochka.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() { return productRepository.findAll(); }

    public List<Product> getAllByCategory(String category) { return productRepository.findAllByCategory(category); }

    public Product findById(Long id) { return productRepository.findProductById(id); }

    public Product create(Product product) { return productRepository.save(product); }
}
