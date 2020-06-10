package com.example.inventoryback.repositories;

import com.example.inventoryback.models.ProductsByStore;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsByStoreRepository extends JpaRepository<ProductsByStore, Long> {

  public List<ProductsByStore> getByStoreId(Long id);

}
