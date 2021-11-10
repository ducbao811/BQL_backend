package com.bql_backend.bql_backend.repository;

import com.bql_backend.bql_backend.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("http://localhost:3000")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    Page findByNameStartsWith(@Param("name") String name, Pageable p);

    @RestResource(path = "nameContaining", rel = "nameContaining")
    Page findByNameContaining(@Param("name") String name, Pageable p);

    @RestResource(path = "priceBetween", rel = "priceBetween")
    Page findByPriceBetween(@Param("value1") Float value1, @Param("value2") Float value2, Pageable p);

    @Query(value = "SELECT * from product order by create_date desc limit 15", nativeQuery = true)
    @RestResource(path = "newRelease", rel = "newRelease")
    List<Product> findNewRelease();

    Page findByCategory(@Param("category") String category, Pageable p);
}
