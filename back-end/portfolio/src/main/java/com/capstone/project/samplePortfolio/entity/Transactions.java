package com.capstone.project.samplePortfolio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Transactions {

    private String type;
    private Date createdAt;
    private Integer quantity;
    private Double amount;
}
