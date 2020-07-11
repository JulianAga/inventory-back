package com.example.inventoryback.controllers;

import com.example.inventoryback.dto.UserRequestDto;
import com.example.inventoryback.exceptions.ResourceAlreadyExistsException;
import com.example.inventoryback.exceptions.RoleNotFoundException;
import com.example.inventoryback.exceptions.UserNotExistsException;
import com.example.inventoryback.models.User;
import com.example.inventoryback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller

@Repository
@RequestMapping(value = "users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> findAll(){
        return this.userService.findAll();
    }

    @RequestMapping(value= "")
    public String getAllUsers(Model model){
        model.addAttribute("users", this.findAll());
        return "users/all";
    }

    public User login(String username, String password)
            throws UserNotExistsException, ValidationException, NoSuchAlgorithmException {
        if ((username != null) && (password != null)) {
            return userService.login(username, password);
        } else {
            throw new ValidationException();
        }
    }

    public User save(@RequestBody UserRequestDto client)
            throws ResourceAlreadyExistsException {
        return this.userService.saveDto(client);
    }

    public User findById(@PathVariable Long id) throws UserNotExistsException {
        User client = this.userService.findById(id);
        if (client.isActive()== Boolean.TRUE) {
            return client;
        } else {
            throw new UserNotExistsException();
        }

    }

    public User update(Long id, UserRequestDto client)
            throws UserNotExistsException, ResourceAlreadyExistsException, NoSuchAlgorithmException, RoleNotFoundException {
        return this.userService.update(id, client);
    }

    public void deleteById(Long id) throws UserNotExistsException {
        this.userService.deleteById(id);
    }

}
