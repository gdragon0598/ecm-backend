package com.huan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ToString.Exclude
    @Column(name = "created_at",  nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @ToString.Exclude
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ToString.Exclude
    @Column(name = "created_by")
    private Long createdBy;

    @ToString.Exclude
    @Column(name = "updated_by")
    private Long updatedBy;

    @ToString.Exclude
    @OneToMany(mappedBy = "primaryKey.role", cascade = CascadeType.ALL)
    Set<UserRole> userRoleSet = new HashSet<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    List<AccessGroup> accessGroupList;
}
