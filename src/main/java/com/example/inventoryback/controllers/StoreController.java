package com.example.inventoryback.controllers;

import com.example.inventoryback.models.Store;
import com.example.inventoryback.services.ProductByStoreService;
import com.example.inventoryback.services.StoreService;
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

@RequestMapping("/store")
public class StoreController {

  @Autowired
  public StoreService storeService;

  @Autowired
  private ProductByStoreService productByStoreService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public String listar(Model model) {
    model.addAttribute("stores", storeService.findAll());
    model.addAttribute("title", "Lista de Locales");
    return "all";
  }

  @RequestMapping(value = "/form")
  public String crear(Map<String, Object> model) {
    Store store = new Store();
    model.put("store", store);
    model.put("title", "Añadir local");
    return "form";
  }

  @RequestMapping(value = "/form/{id}")
  public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
    Store store = null;
    if (id > 0) {
      store = storeService.findById(id);
    } else {
      return "redirect:/store/all";
    }

    model.put("store", store);
    model.put("title", "Editar local");

    return "form";

  }

  @RequestMapping(value = "/form", method = RequestMethod.POST)
  public String save(@Valid Store store, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("titulo", "Añadir Local");

      return "form";
    }
    storeService.save(store);
    return "redirect:all";
  }

  @RequestMapping(value = "/eliminar/{id}")
  public String eliminar(@PathVariable(value = "id") Long id) {

    if (id > 0) {
      storeService.delete(id);
    }
    return "redirect:/store/all";
  }


}
