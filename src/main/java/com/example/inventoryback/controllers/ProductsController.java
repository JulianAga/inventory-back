package com.example.inventoryback.controllers;

import com.example.inventoryback.models.Product;
import com.example.inventoryback.models.Store;
import com.example.inventoryback.services.ProductService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

@RequestMapping("/product")
public class ProductsController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public String listar(Model model) {
    model.addAttribute("products", productService.findAll());
    model.addAttribute("title", "Lista de productos");
    return "products/all";
  }

  @RequestMapping(value = "/form")
  public String crear(Map<String, Object> model) {
    Product product = new Product();
    model.put("product", product);
    model.put("title", "Añadir producto");
    return "products/form";
  }

  @RequestMapping(value = "/form/{id}")
  public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
    Product product = null;
    if (id > 0) {
      product = productService.findById(id);
    } else {
      return "redirect:/store/all";
    }

    model.put("product", product);
    model.put("title", "Editar producto");

    return "products/form";

  }

  @RequestMapping(value = "/form", method = RequestMethod.POST)
  public String save(@Valid Product product, BindingResult result, Model model) {
    productService.save(product);
    return "redirect:all";
  }
}
