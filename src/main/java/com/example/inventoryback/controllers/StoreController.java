package com.example.inventoryback.controllers;

import com.example.inventoryback.entities.requests.RequestStore;
import com.example.inventoryback.entities.responses.ResponseMessage;
import com.example.inventoryback.entities.responses.ResponseStore;
import com.example.inventoryback.models.Store;
import com.example.inventoryback.services.StoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

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
  public Store get(@RequestBody RequestStore store){
    return storeService.findStoreByName(store);
  }

  @PutMapping(value = {"/{id}"})
  public void modify(@RequestBody Store store, @PathVariable Long id) {
    storeService.update(store, id);
  }

  @DeleteMapping(value = {"/{id}"})
  public void delete(@PathVariable Long id) {
    storeService.deleteById(id);
  }
}
