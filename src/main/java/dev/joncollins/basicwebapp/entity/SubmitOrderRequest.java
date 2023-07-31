package dev.joncollins.basicwebapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubmitOrderRequest {
    private String order_id;
    private String user_token;
    private double total;
    private List<String> productIds;
}
