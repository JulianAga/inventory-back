package com.example.inventoryback.services;

import com.example.inventoryback.entities.requests.RequestStore;
import com.example.inventoryback.entities.responses.ResponseStore;
import com.example.inventoryback.models.Store;
import com.example.inventoryback.repositories.StoreRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;
import sun.misc.Request;

@Service
public class StoreService {

  @Autowired
  private StoreRepository storeRepository;

  public void save(Store store) {
    if (!storeIsValid(store)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "error creating store, check the name, address or city");
    }
    {
      this.storeRepository.save(store);
    }
  }

  public boolean storeIsValid(Store store) {

    boolean result = true;

    if (store.getName().isEmpty()) {
      result = false;
    }

    if (store.getAddress().isEmpty()) {
      result = false;
    }

    if (store.getCity().isEmpty()) {
      result = false;
    }

    if (store.getName().length() < 2 || store.getName().length() > 100) {
      result = false;
    }

    if (store.getAddress().length() < 2 || store.getAddress().length() > 100) {
      result = false;
    }

    if (store.getCity().length() < 2 || store.getCity().length() > 100) {
      result = false;
    }

    if(isStoreByName(store.getName())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The name already exist");
    }

    return result;
  }

  public boolean isStoreByName(String name) {
    return this.storeRepository.findAll()
        .stream().filter(store -> store.getName().equals(name))
        .map(store -> Boolean.TRUE).findAny().orElse(Boolean.FALSE);
  }

  public Store findStoreByName(RequestStore requestStore){
    return this.storeRepository.findAll()
        .stream().filter(store -> store.getName().equals(requestStore.getName())).findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "The store does not exist"));
  }

  public void update(Store newStore, Long idStore) {
    Store oldStore = this.findById(idStore);
    oldStore.setName(newStore.getName());
    oldStore.setAddress(newStore.getAddress());
    oldStore.setCity(newStore.getCity());
    this.save(oldStore);
  }

  public Store findById(Long id) {
    return storeRepository.findById(id).orElseThrow(
        (() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store not found")));
  }

  public List<ResponseStore> findAll() {
    return this.storeRepository.findAll().stream()
        .map(store ->
            ResponseStore.builder()
                .address(store.getAddress())
                .city(store.getCity())
                .name(store.getName())
                .id(store.getId())
                .build()
        ).collect(Collectors.toList());
  }

  public void deleteById(Long id) {
    this.storeRepository.deleteById(id);
  }
}
