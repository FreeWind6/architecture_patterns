package ru.geekbrains.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person implements Client {
    private Integer id;
    private String passportId;
    private String name;

    @Override
    public String showInfo() {
        return String.format("Клиент : %s, паспорт : %s", name, passportId);
    }
}
