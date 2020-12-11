package ru.geekbrains.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Route {
    private Branch from;
    private List<Order> orders;
    private LocalDate departureDate;
}
