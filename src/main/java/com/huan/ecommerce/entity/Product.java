package com.huan.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    @Column(name = "image_url")
    private String imageUrl;

    private Integer sale;

    @Column(name = "supplier_id", nullable = false)
    private Integer supplierId;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "number_sold_item")
    private Integer numberSoldItems;
    private Integer total;

    @Column(name = "created_at",  nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "product")
    @JsonManagedReference
    private ProductDetail productDetail;

    @OneToMany(mappedBy = "product" ,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> orderDetailList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnore
    private List<Comment> commentList;
}
