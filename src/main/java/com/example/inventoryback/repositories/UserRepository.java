package com.example.inventoryback.repositories;

import com.example.inventoryback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User getByMailAndPassword(String mail, String password);

    public User findByMail(String mail);

}
