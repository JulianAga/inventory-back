package com.example.inventoryback.controllers;

import com.example.inventoryback.models.Product;
import com.example.inventoryback.services.ProductByStoreService;
import com.example.inventoryback.services.ProductService;
import com.example.inventoryback.services.StoreService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

@Repository
@RequestMapping(value = "productByStore")
public class ProductsByStoreController {

  @Autowired
  private ProductByStoreService productByStoreService;

  @Autowired
  private ProductService productService;

  @Autowired
  private StoreService storeService;


  @RequestMapping(value = "/{idStore}")
  public String getStoreProducts(@PathVariable Long idStore, Model model) {
    model.addAttribute("productsByStore", productByStoreService.getByStoreId(idStore));
    model.addAttribute("products", productService.findAll());
    model.addAttribute("title", "Lista de productos de " + storeService.findById(idStore).getName());
    model.addAttribute("id", idStore);

    return "productsByStore/all";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  public String save(Product product, @PathVariable Long id) {
    this.productByStoreService.saveProduct(product, id);
    return "redirect:/productByStore/{id}";
  }


  @RequestMapping(value = "/all/{idStore}")
  public String crear(Map<String, Object> model, @PathVariable Long idStore) {
    Product product = new Product();
    model.put("product", product);
    model.put("title", "Lista de productos de" + this.storeService.findById(idStore).getName());
    model.put("id", idStore);
    return "productsByStore/{id}";
  }


}