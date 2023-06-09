package com.ahmetguzeller.productmanager.service;

import com.ahmetguzeller.productmanager.components.ProductConverter;
import com.ahmetguzeller.productmanager.model.Product;
import com.ahmetguzeller.productmanager.repository.ProductRepository;
import com.ahmetguzeller.productmanager.request.CreateProductRequest;
import com.ahmetguzeller.productmanager.request.UpdateProductRequest;
import com.ahmetguzeller.productmanager.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    public ProductService(ProductRepository productRepository,
                          ProductConverter productConverter){

        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        log.debug("[{}][createdproduct] -> request :{}", this.getClass().getSimpleName(), createProductRequest);
        try {
            Product product = Product.builder()

                    .productName(createProductRequest.getProductName())
                    .productPrice(createProductRequest.getProductPrice())
                    .quantity(createProductRequest.getQuantity())
                    .isDeleted(false)
                    .build();
            productRepository.save(product);
            log.debug("[{}][createdproduct] -> request :{}", this.getClass().getSimpleName(), createProductRequest);

            return productConverter.convert(product);
        }
        catch (Exception exception){
            throw new RuntimeException();
           // log.debug(exception.getMessage()); //düzenle !
        }
    }

    public ProductResponse getProduct(Integer productId) {
        log.debug("[{}][getproduct] -> request :{}", this.getClass().getSimpleName(), productId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        Product product;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();

        } else {
            throw new RuntimeException();
        }

        log.debug("[{}][getproduct] -> request :{}", this.getClass().getSimpleName(), productId);

        return productConverter.convert(product);
    }

    public ProductResponse updateProduct(Integer productId , UpdateProductRequest updateProductRequest ){
        log.debug("[{}][updateProduct] -> request :{}", this.getClass().getSimpleName(),productId);

        Optional<Product> optionalProduct=  productRepository.findById(productId);

        optionalProduct.ifPresentOrElse(product -> {
            product.setProductName(updateProductRequest.getProductName());
            product.setProductPrice(updateProductRequest.getProductPrice());
            product.setProductUpdatedDate(new Date());
            product.setQuantity(updateProductRequest.getQuantity());

            productRepository.save(product);

        }, RuntimeException::new);
        return productConverter.convert(optionalProduct.get());
    }

   public List<ProductResponse> getAllByProductIdAndNotDeleted(){

        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponseList= new ArrayList<>();

        if(productList.isEmpty()){

            throw new RuntimeException(); //düzenlenecek !
        }

       for (Product products: productList) {
        productResponseList.add( productConverter.convert(products));
       }

       return productResponseList;
   }

    public void deleteProduct(Integer productId){
        log.debug("[{}][deleteProduct] -> request :{}", this.getClass().getSimpleName(),productId);
        Optional<Product> product = productRepository.findById(productId);

        product.ifPresentOrElse(product1 -> {
                   product1.setDeleted(true);
                   product1.setProductUpdatedDate(new Date());

    productRepository.save(product1);
} , () -> new RuntimeException());

        log.debug("[{}][updateProduct] -> request :{}", this.getClass().getSimpleName(),productId);
    }




}

