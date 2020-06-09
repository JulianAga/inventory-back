package com.example.inventoryback.controllers;

import com.example.inventoryback.entities.requests.RequestStore;
import com.example.inventoryback.entities.responses.ResponseMessage;
import com.example.inventoryback.entities.responses.ResponseStore;
import com.example.inventoryback.models.Store;
import com.example.inventoryback.services.StoreService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller

@RequestMapping("/store")
public class StoreController {

  @Autowired
  private StoreService storeService;

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseMessage insert(@RequestBody Store store) {
    storeService.save(store);
    return new ResponseMessage("Store created successfully");
  }

  @GetMapping("")
  public List<ResponseStore> getAll() {
    return storeService.findAll();
  }

  @GetMapping("search")
  public Store get(@RequestBody RequestStore store) {
    return storeService.findStoreByName(store);
  }

  @PutMapping(value = {"/{id}"})
  public void modify(@RequestBody Store store, @PathVariable Long id) {
    storeService.update(store, id);
  }

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

  @RequestMapping(value="/form/{id}")
  public String editar(@PathVariable(value="id") Long id, Map <String,Object> model)
  {
    Store store = null;
    if(id > 0)
    {
      store = storeService.findById(id);
    }
    else
    {
      return "redirect:/store/all";
    }

    model.put("store", store);
    model.put("title" , "Editar local");

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
