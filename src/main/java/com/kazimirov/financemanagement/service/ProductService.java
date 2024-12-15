package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ProductResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private ValidatorForAddProductAnOrder validatorForAddProductAnOrder;

    @Autowired
    public ProductService(ProductRepository productRepository, ValidatorForAddProductAnOrder validatorForAddProductAnOrder) {
        this.productRepository = productRepository;
        this.validatorForAddProductAnOrder = validatorForAddProductAnOrder;
    }

    public ProductEntity createProduct(ProductEntity productEntity) {
        if (!validatorForAddProductAnOrder.validate(productEntity)) {
            return productRepository.save(productEntity);
        } else {
            //todo вывести сообщение об ошибке
            throw new IllegalArgumentException("Err: Товар уже в списке");
        }
    }

    public ProductEntity addProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public ProductEntity editProduct(ProductEntity editProductEntity, Long productID) {
        ProductEntity existingProduct = getProductById(productID);

        existingProduct.setProductName(editProductEntity.getProductName());
        existingProduct.setPrice(editProductEntity.getPrice());
        existingProduct.setNote(editProductEntity.getNote());

        return productRepository.save(existingProduct);
    }

    public void addNewProductInOrder(OrderEntity orderEntity,
                                     List<String> productNames,
                                     List<Integer> quantities) {

        List<ProductEntity> existingProducts = productRepository.findByOrderEntityId(orderEntity.getId());

        Map<String, ProductEntity> existingProductMap = existingProducts.stream()
                .collect(Collectors.toMap(
                        product -> generateKey(product.getProductName(), product.getPrice()),
                        product -> product
                ));

        List<ProductEntity> newProducts = new ArrayList<>();

        for (int i = 0; i < productNames.size(); i++) {
            ProductEntity productEntity = new ProductEntity();

            ProductEntity product = getByName(productNames.get(i));
            productEntity.setProductName(productNames.get(i));
            productEntity.setQuantity(quantities.get(i));
            productEntity.setPrice(product.getPrice());
            productEntity.setOrderEntity(orderEntity);
            newProducts.add(productEntity);
        }

        for (ProductEntity newProduct : newProducts) {
            String key = generateKey(newProduct.getProductName(), newProduct.getPrice());

            if (existingProductMap.containsKey(key)) {
                ProductEntity existingProduct = existingProductMap.get(key);
                existingProduct.setQuantity(existingProduct.getQuantity() + newProduct.getQuantity());
            } else {
                existingProducts.add(newProduct);

                String key1 = generateKey(newProduct.getProductName(), newProduct.getPrice());
                existingProductMap.put(key1, newProduct);
            }
        }

        for (ProductEntity productEntity : existingProducts) {
            addProduct(productEntity);
        }
    }


    private String generateKey(String productName, int price) {
        return productName + "|" + price;
    }

    public List<ProductResponse> getProducts() {

        return getProductsWithoutOrder().stream()
                .map(ProductResponseFactory::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public List<ProductEntity> getProductsWithoutOrder() {
        return productRepository.findByOrderEntityIsNull();
    }

    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);
    }

    public ProductEntity getByName(String productName) {
        List<ProductEntity> productEntityList = getProductsWithoutOrder();

        return productEntityList.stream()
                .filter(product -> product.getProductName().equals(productName))
                .filter(product -> product.getOrderEntity() == null)
                .findFirst()
                .get();
    }

    public ProductEntity getProductById(Long productId) {
        return productRepository.findById(productId)
                .get();
    }
}
