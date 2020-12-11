package ru.geekbrains.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Branch {
    private Integer id;
    private String name;
    private String latitude;
    private String longitude;
}
