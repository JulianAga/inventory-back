package com.example.inventoryback.services;

import com.example.inventoryback.dto.UserRequestDto;
import com.example.inventoryback.exceptions.ResourceAlreadyExistsException;
import com.example.inventoryback.exceptions.RoleNotFoundException;
import com.example.inventoryback.exceptions.UserNotExistsException;
import com.example.inventoryback.models.User;
import com.example.inventoryback.repositories.UserRepository;
import com.example.inventoryback.restUtils.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    public void save(User user){
        this.userRepository.save(user);
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User login(String mail, String password)
            throws UserNotExistsException, NoSuchAlgorithmException {
        User user = userRepository.getByMailAndPassword(mail, HashPassword.hashPassword(password));
        return Optional.ofNullable(user).orElseThrow(UserNotExistsException::new);
    }

    public User saveDto(UserRequestDto userDto)
            throws ResourceAlreadyExistsException {
        User user = new User();

        try {
            user.setMail(userDto.getMail());
            user.setAddress(userDto.getAddress());
            user.setCellphone(userDto.getCellphone());
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            user.setRole(roleService.findById(userDto.getRole()));
            return this.userRepository.save(user);
        } catch (Exception e) {
            throw new ResourceAlreadyExistsException();
        }

    }

    public User findById(Long id) throws UserNotExistsException {
        return this.userRepository.findById(id).orElseThrow(
                UserNotExistsException::new);
    }

    public User update(Long id, UserRequestDto userDto)
            throws UserNotExistsException, NoSuchAlgorithmException, ResourceAlreadyExistsException, RoleNotFoundException {
        User user = this.findById(id);
        user.setMail(userDto.getMail());
        user.setAddress(userDto.getAddress());
        user.setCellphone(userDto.getCellphone());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setRole(roleService.findById(userDto.getRole()));

        return this.userRepository.save(user);
    }

    public void deleteById(Long id) throws UserNotExistsException {
        User client = this.findById(id);
        client.setActive(Boolean.FALSE);
        this.userRepository.save(client);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] data = password.getBytes();
        m.update(data, 0, data.length);
        BigInteger i = new BigInteger(1, m.digest());
        System.out.println(String.format("%1$032X", i));
        return String.format("%1$032X", i);
    }

}
