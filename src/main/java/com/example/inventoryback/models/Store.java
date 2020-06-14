package com.example.inventoryback.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(name = "stores")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@ToString
public class Store implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 2, max = 100)
  @JsonProperty(value = "name")
  @NotEmpty
  @Column(name = "name")
  private String name;

  @Size(min = 2, max = 100)
  @JsonProperty(value = "address")
  @NotEmpty
  @Column(name = "address")
  private String address;

  @Size(min = 2, max = 100)
  @JsonProperty(value = "city")
  @NotEmpty
  @Column(name = "city")
  private String city;

  @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
  @ToString.Exclude
  private List<ProductsByStore> productsByStores;
}
