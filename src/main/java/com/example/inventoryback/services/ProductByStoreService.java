package com.example.inventoryback.services;

import com.example.inventoryback.exceptions.StockIsNegativeException;
import com.example.inventoryback.models.Product;
import com.example.inventoryback.models.ProductsByStore;
import com.example.inventoryback.models.Store;
import com.example.inventoryback.repositories.ProductRepository;
import com.example.inventoryback.repositories.ProductsByStoreRepository;
import com.example.inventoryback.repositories.StoreRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductByStoreService {

    @Autowired
    private ProductsByStoreRepository productsByStoreRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

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
        .product(productService.findById(idProduct.getId()))
        .Stock(0L)
        .build();
    this.productsByStoreRepository.save(productsByStore);
  }
    

   /* public void updateProduct(ProductsByStore product, Long id) throws Exception {
        ProductsByStore productsByStore = productsByStoreRepository.findById(id).orElseThrow(Exception::new);
        productsByStore.setStock(product.getStock());

        this.productsByStoreRepository.save(productsByStore);
    }*/

    public ProductsByStore findById(Long id) {
        return this.productsByStoreRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "product by store not found"));
    }

    public void removeByStoreId(Long id) {
        this.productsByStoreRepository.deleteByStore(id);
    }

    public void addStock(Long store_id, Long product_id, Long stockAdded) {
        this.productsByStoreRepository.addStock(store_id, product_id, stockAdded);
    }

    public void removeStock(Long store_id, Long product_id, Long stockRemoved) throws StockIsNegativeException {
        ProductsByStore dummy = this.productsByStoreRepository.getByProductIdAndStoreId(product_id, store_id);
        if (dummy.getStock() - stockRemoved < 0) {
            throw new StockIsNegativeException();
        } else {
            this.productsByStoreRepository.removeStock(store_id, product_id, stockRemoved);
        }
    }


}
