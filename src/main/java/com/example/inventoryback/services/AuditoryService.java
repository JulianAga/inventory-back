package com.example.inventoryback.services;

import com.example.inventoryback.models.Auditory;
import com.example.inventoryback.repositories.AuditoryRepository;
import com.example.inventoryback.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoryService {

    @Autowired
    private AuditoryRepository auditoryRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private StoreRepository storeRepository;

    public void save(Long idProduct, Long idStore, Long stockModified, String reason, String addedOrEliminated) {
        {
            Auditory auditory = Auditory.builder()
                    .store(storeRepository.getOne(idStore))
                    .product(productService.findById(idProduct))
                    .stockModified(stockModified)
                    .reason(reason)
                    .addedOrEliminated(addedOrEliminated)
                    .build();
            this.auditoryRepository.save(auditory);
        }
    }

}
