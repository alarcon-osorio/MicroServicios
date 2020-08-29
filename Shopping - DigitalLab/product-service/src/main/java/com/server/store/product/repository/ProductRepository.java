package com.server.store.product.repository;

import com.server.store.product.entity.Category;
import com.server.store.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    public List<Product> findByCategory(Category category);
}
