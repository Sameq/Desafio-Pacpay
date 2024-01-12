package com.picpay.picpay.domain.transection;

import com.picpay.picpay.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transection")
@Table(name = "transection")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "sander_id")
    private User sander;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private LocalDateTime timestamp;

}
