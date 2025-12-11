package com.springboot.jpaTutes.repository;

import com.springboot.jpaTutes.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    //List<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    @Query("select e from ProductEntity e where e.title =:title  and e.price =:price")
    ProductEntity findByTitleAndPrice(String title, BigDecimal price);

    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findBy(Sort sort);
}
