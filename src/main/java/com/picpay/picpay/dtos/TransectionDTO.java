package com.picpay.picpay.dtos;

import java.math.BigDecimal;

public record TransectionDTO(BigDecimal value, Long sanderId, Long receiverId) {
}
