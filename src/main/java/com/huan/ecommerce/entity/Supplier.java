package com.huan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "supplier")
@PrimaryKeyJoinColumn(name = "user_id")
public class Supplier extends User {
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contactNumber")
    private String contactNumber;
    private String description;
}
