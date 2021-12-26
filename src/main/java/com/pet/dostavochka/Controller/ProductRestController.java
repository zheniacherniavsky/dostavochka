package com.pet.dostavochka.Controller;

import com.pet.dostavochka.AOP.LogAnnotation;
import com.pet.dostavochka.Model.Product;
import com.pet.dostavochka.Services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "list")
    @LogAnnotation
    public ResponseEntity<List<Product>> getProducts(@RequestParam Map<String, String> mapParam) {
        String category = mapParam.get("category");
        List<Product> products = productService.getAllByCategory(category);
        log.info("Get request : /api/v1/product/list");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
