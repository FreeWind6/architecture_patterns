package ru.geekbrains.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cargo {
    private Integer id;
    private String name;
    private boolean isFragile;
}
