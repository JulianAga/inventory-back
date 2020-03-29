package com.example.inventoryback.controllers;

import com.example.inventoryback.entities.requests.RequestProduct;
import com.example.inventoryback.entities.responses.ResponseMessage;
import com.example.inventoryback.models.Product;
import com.example.inventoryback.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/product")
public class ProductsController {

  @Autowired
  private ProductService productService;

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseMessage insert(@RequestBody Product product) {
    productService.save(product);
    return new ResponseMessage("Product created successfully");
  }

  @GetMapping("")
  public List<Product> getAll() {
    return productService.findAll();
  }

  @GetMapping("search")
  public Product get(@RequestBody RequestProduct product) {
    return productService.findProductByName(product);
  }

  @PutMapping(value = {"/{id}"})
  public void modify(@RequestBody Product product, @PathVariable Long id) {
    productService.update(product, id);
  }

  @DeleteMapping(value = {"/{id}"})
  public void delete(@PathVariable Long id) {
    productService.deleteById(id);
  }
}
