package com.example.inventoryback.repositories;

import com.example.inventoryback.models.Product;
import com.example.inventoryback.models.ProductsByStore;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsByStoreRepository extends JpaRepository<ProductsByStore, Long> {

  public List<ProductsByStore> getByStoreId(Long id);

  @Transactional
  @Modifying
  @Query(value = "delete from products_by_store where store_id = ?1", nativeQuery = true)
  public void deleteByStore(Long id);

  public void deleteByStoreId(Long id);

  @Transactional
  @Modifying
  @Query(value= "UPDATE products_by_store SET stock = stock + ?3 WHERE store_id= ?1 AND product_id= ?2", nativeQuery = true)
  public void addStock(Long store_id, Long product_id, Long stockAdded);

  @Transactional
  @Modifying
  @Query(value= "UPDATE products_by_store SET stock = stock - ?3 WHERE store_id= ?1 AND product_id= ?2", nativeQuery = true)
  public void removeStock(Long store_id, Long product_id, Long stockRemoved);

  public ProductsByStore getByProductIdAndStoreId( Long productId, Long storeId);
}
