package com.example.inventoryback.services;

import com.example.inventoryback.entities.requests.RequestProduct;
import com.example.inventoryback.models.Product;
import com.example.inventoryback.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public void save(Product product) {
    if (!productIsValid(product)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "error creating product, check the name");
    }
    {
      this.productRepository.save(product);
    }
  }

  public boolean productIsValid(Product product) {

    boolean result = true;

    if (product.getName().isEmpty()) {
      result = false;
    }

    if (product.getName().length() < 2 || product.getName().length() > 100) {
      result = false;
    }

    return result;
  }

  public Product findProductByName(RequestProduct requestProduct) {
    return this.productRepository.findAll()
        .stream().filter(product -> product.getName().equals(requestProduct.getName())).findAny()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "The product does not exist"));
  }

  public void update(Product newProduct, Long idProduct) {
    Product oldProduct = this.findById(idProduct);
    oldProduct.setName(newProduct.getName());
    oldProduct.setCode(newProduct.getCode());
    oldProduct.setInitialPrice(newProduct.getInitialPrice());
    oldProduct.setSellingPrice(newProduct.getSellingPrice());
    oldProduct.setPhoto(newProduct.getPhoto());
    this.save(oldProduct);
  }

  public Product findById(Long id) {
    return productRepository.findById(id).orElseThrow(
        (() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store not found")));
  }

  public List<Product> findAll() {
    return this.productRepository.findAll();
  }

  public void deleteById(Long id) {
    this.productRepository.deleteById(id);
  }

}
