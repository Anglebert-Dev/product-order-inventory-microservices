package com.eshop.productservice.Service;


import com.eshop.productservice.DTO.ProductRequest;
import com.eshop.productservice.DTO.ProductResponse;
import com.eshop.productservice.Model.Product;
import com.eshop.productservice.Repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepo.save(product);
        log.info("Product {} created" , product.getId());
    }

//    public List<ProductResponse> getAllProducts() {
//        List<Product> products = productRepo.findAll();
//
//        return products.stream().map(this::mapToProductResponse).toList();
//    }
//
//    private ProductResponse mapToProductResponse(Product product) {
//        return ProductResponse.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .description(product.getDescription())
//                .price(product.getPrice())
//                .build();
//    }

    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll().stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
