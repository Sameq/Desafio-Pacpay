package com.picpay.picpay.dtos;

import com.picpay.picpay.domain.UserType;

import java.math.BigDecimal;

public record UserDTO(String fistName, String lastName, String document, String email, String password, BigDecimal balance, UserType userType) {
}
