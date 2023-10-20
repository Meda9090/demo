package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prod_tab")
public class Product {

    @GeneratedValue
    @Id
    @Column(name = "p_id")
    private Integer prodId;

    @Column(name = "p_code")
    private String prodCode;

    @Column(name = "p_cost")
    private Double prodCost; //Стоимость продукта

    @Column(name = "p_ven")
    private String prodVendor; //Продавец

    @Column(name = "p_skidka")
    private Double prodSkidka;

    @Column(name = "p_Gst")
    private Double prodGst; //Сколько затрать входит в стоимость
}
