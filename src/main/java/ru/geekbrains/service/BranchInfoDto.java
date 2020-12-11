package ru.geekbrains.service;

import lombok.Data;
import lombok.Getter;

@Data
public class BranchInfoDto {
    private String name;
    private String latitude;
    private String longitude;
}
