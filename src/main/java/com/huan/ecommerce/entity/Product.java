package com.huan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "image_url")
    private String imageUrl;

    private Integer sale;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "user_id")
    private Supplier supplier;

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
    private ProductDetail productDetail;

    @OneToMany(mappedBy = "product" ,fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetailList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Comment> commentList;
}
