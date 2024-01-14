package com.picpay.picpay.service;

import com.picpay.picpay.domain.User;
import com.picpay.picpay.domain.UserType;
import com.picpay.picpay.dtos.UserDTO;
import com.picpay.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransection(User sander, BigDecimal amount) throws Exception {
        if(sander.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario do tipo Logista não é autorizado!");
        }
        if(sander.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }
    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }
    public User findUserById(Long id) throws Exception{
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }
}
