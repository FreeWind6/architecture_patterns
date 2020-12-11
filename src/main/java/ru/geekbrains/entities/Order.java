package ru.geekbrains.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Order {
    private Integer id;
    private LocalDate createdDate;
    private Client client;
    private Cargo cargo;
    private Branch destination;
    private OrderStatus status;
}
