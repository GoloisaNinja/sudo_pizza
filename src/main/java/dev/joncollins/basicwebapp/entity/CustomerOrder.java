package dev.joncollins.basicwebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "customer_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @Column(name = "order_id")
    private String order_id;
    @Column(name = "user_token")
    private String user_token;
    @Column(name = "created_on")
    @CreationTimestamp
    private Date created_on;
    @Column(name = "total")
    private double total;
}
