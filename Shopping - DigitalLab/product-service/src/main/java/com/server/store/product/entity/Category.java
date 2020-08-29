package com.server.store.product.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name ="tbl_categories")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
}
