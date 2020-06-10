package com.example.inventoryback.services;

import com.example.inventoryback.models.Product;
import com.example.inventoryback.models.ProductsByStore;
import com.example.inventoryback.repositories.ProductRepository;
import com.example.inventoryback.repositories.ProductsByStoreRepository;
import com.example.inventoryback.repositories.StoreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductByStoreService {

  @Autowired
  private ProductsByStoreRepository productsByStoreRepository;

  @Autowired
  private StoreRepository storeRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  public ProductByStoreService(
      ProductsByStoreRepository productsByStoreRepository) {
    this.productsByStoreRepository = productsByStoreRepository;
  }

  public List<ProductsByStore> getByStoreId(Long id) {
    return this.productsByStoreRepository.getByStoreId(id);
  }

  public void saveProduct(Product idProduct, Long idStore) {

    ProductsByStore productsByStore = ProductsByStore.builder()
        .store(storeRepository.getOne(idStore))
        .product(productRepository.getOne(idProduct.getId()))
        .Stock(0L)
        .build();
    this.productsByStoreRepository.save(productsByStore);
  }

}
