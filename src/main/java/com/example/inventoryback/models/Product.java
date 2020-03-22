package com.example.inventoryback.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@ToString
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 2, max = 100)
  @JsonProperty(value = "name")
  @NotEmpty
  @Column(name = "name")
  private String name;

  private String photo;

  @Column(name = "code")
  @JsonProperty(value = "code")
  private String code;

  @Column(name = "initial_price")
  @JsonProperty(value = "initial_price")
  private Float initialPrice;

  @Column(name = "selling_price")
  @JsonProperty(value = "selling_price")
  private Float sellingPrice;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
  @ToString.Exclude
  private List<ProductsByStore> productsByStores;

}

