package com.example.inventoryback.entities.requests;

import com.example.inventoryback.models.ProductsByStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestModifyStock {

    private Long stockModified;

    private ProductsByStore productsByStore;
}
