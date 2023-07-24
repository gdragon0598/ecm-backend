package com.huan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "supplier")
@PrimaryKeyJoinColumn(name = "user_id")
public class Supplier extends User {
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contact_number")
    private String contactNumber;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Product> productList;
}
