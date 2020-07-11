package com.example.inventoryback.controllers;

import com.example.inventoryback.dto.UserRequestDto;
import com.example.inventoryback.exceptions.ResourceAlreadyExistsException;
import com.example.inventoryback.exceptions.RoleNotFoundException;
import com.example.inventoryback.exceptions.UserNotExistsException;
import com.example.inventoryback.models.Product;
import com.example.inventoryback.models.User;
import com.example.inventoryback.services.RoleService;
import com.example.inventoryback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Controller

@Repository
@RequestMapping(value = "users")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService= roleService;
    }

    public List<User> findAll(){
        return this.userService.findAll();
    }

    @RequestMapping(value= "/all", method = RequestMethod.GET)
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

    @RequestMapping(value = "/form")
    public String createNewUser(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        model.put("roles", roleService.findAll());
        model.put("title", "Añadir usuario");
        return "users/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String addNewUser(@Valid User user, BindingResult result, Model model) {
        userService.save(user);
        return "redirect:all";
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
