package ru.geekbrains.repository.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class OrderView {
    private Integer orderId;
    private Timestamp createdDate;
    private String status;
    private Integer clientId;
    private String clientName;
    private String passportId;
    private Integer branchId;
    private String latitude;
    private String longitude;
    private Integer cargoId;
    private String cargoName;
    private Boolean isFragile;
}
