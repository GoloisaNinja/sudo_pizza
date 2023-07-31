package dev.joncollins.basicwebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "description")
    private String description;
    @Column(name = "slice_count")
    private Integer slice_count;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private double price;
//    @OneToOne(fetch = FetchType.LAZY,mappedBy = "product")
//    private OrderProduct orderProduct;
}
