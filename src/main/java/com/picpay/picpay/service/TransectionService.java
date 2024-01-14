package com.picpay.picpay.service;

import com.picpay.picpay.domain.User;
import com.picpay.picpay.domain.transection.Transection;
import com.picpay.picpay.dtos.TransectionDTO;
import com.picpay.picpay.repository.TransectionRepository;
import com.picpay.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class TransectionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransectionRepository transectionRepository;

    private RestTemplate restTemplate;
    public void createTransection(TransectionDTO transection) throws Exception {
        User sander = this.userService.findUserById(transection.sanderId());
        User receiver = this.userService.findUserById(transection.receiverId());

        userService.validateTransection(sander, transection.value());

        if(!this.authorizeTransection(sander, transection.value())){
            throw new Exception("Transação nao autorizada!");
        }

        Transection newTransection = new Transection();
        newTransection.setAmount(transection.value());
        newTransection.setSander(sander);
        newTransection.setReceiver(receiver);
        newTransection.setTimestamp(LocalDateTime.now());

        sander.setBalance(sander.getBalance().subtract(transection.value()));
        receiver.setBalance(receiver.getBalance().add(transection.value()));

        this.transectionRepository.save(newTransection);
        this.userService.saveUser(sander);
        this.userService.saveUser(receiver);

    }

    public boolean authorizeTransection(User sander, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else  return false;
    }
}
