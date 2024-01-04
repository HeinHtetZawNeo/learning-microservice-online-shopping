package com.learning.productsvc.repository;

import com.learning.productsvc.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
