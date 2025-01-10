package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.ProductResponse;
import com.kazimirov.financemanagement.entity.ClientEntity;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.OrderRepository;
import com.kazimirov.financemanagement.repository.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderEntity orderEntity;
    @Mock
    private ValidatorForAddProductAnOrder validatorForAddProductAnOrder;
    @Mock
    private OrderService orderService;
    @Mock
    private ProductResponseFactory productResponseFactory;
    @InjectMocks
    private ProductService productService;

    private ProductEntity productEntity;

    @Test
    void createProduct_create() {
        productEntity = new ProductEntity();
        productEntity.setPrice(100);

        when(validatorForAddProductAnOrder.validate(productEntity)).thenReturn(false);
        when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductEntity result = productService.createProduct(productEntity);

        verify(productRepository).save(productEntity);

        assertEquals(productEntity.getPrice(), result.getPrice());
    }

    @Test
    void createProduct_Exception() {
        productEntity = new ProductEntity();
        productEntity.setPrice(100);

        when(validatorForAddProductAnOrder.validate(productEntity)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(productEntity));

        verify(productRepository, never()).save(productEntity);
    }

    @Test
    void addProductTest() {
        productEntity = new ProductEntity();
        productEntity.setPrice(100);

        when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductEntity result = productService.addProduct(productEntity);

        verify(productRepository).save(productEntity);

        assertEquals(productEntity.getPrice(), result.getPrice());
    }

    @Test
    void editProductTest() {
        Long productId = 1L;
        productEntity = new ProductEntity();
        productEntity.setProductName("Updated Name");
        productEntity.setPrice(150);
        productEntity.setNote("Updated Note");

        ProductEntity productEntityFromDB = new ProductEntity();
        productEntityFromDB.setProductName("Old Name");
        productEntityFromDB.setPrice(100);
        productEntityFromDB.setNote("Old Note");

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntityFromDB));
        when(productRepository.save(productEntityFromDB)).thenReturn(productEntityFromDB);

        ProductEntity result = productService.editProduct(productEntity, productId);

        verify(productRepository).save(productEntityFromDB);

        assertEquals("Updated Name", productEntityFromDB.getProductName());
        assertEquals(150, productEntityFromDB.getPrice());
        assertEquals("Updated Note", productEntityFromDB.getNote());

        assertEquals(productEntityFromDB, result);
    }

//    @Test
//    void addNewProductInOrder() throws NoSuchFieldException, IllegalAccessException {//новый
////        //входные данные
////        OrderEntity newOrderEntity = new OrderEntity();
////        Field field = OrderEntity.class.getDeclaredField("id");
////        field.setAccessible(true);
////        field.set(newOrderEntity, 1L);
////        newOrderEntity.setNote("Test Note");
////
////        List<String> newProductNames = List.of("Product A");
////        List<Integer> newQuantities = List.of(2);
////        //
////
////        //подготовка данных для переменной List<ProductEntity> existingProducts
//////        ProductEntity productOfOrderFromDB = new ProductEntity();
//////        productOfOrderFromDB.setProductName("Product A");
//////        productOfOrderFromDB.setPrice(111);
//////        productOfOrderFromDB.setQuantity(1);
////
////        when(productRepository.findByOrderEntityId(orderEntity.getId())).thenReturn(List.of());
////        //
////
////        //получение продукта по имени
////        ProductEntity productToOrderFromDB = new ProductEntity();
////        productToOrderFromDB.setProductName("Product A");
////        productToOrderFromDB.setPrice(222);
////
////        when(productRepository.findByOrderEntityIsNull()).thenReturn(List.of(productToOrderFromDB));
////        //
////
////        productService.addNewProductInOrder(newOrderEntity, newProductNames, newQuantities);
//    }

    @Test
    void calculateTotalOldOrderTest() {
        List<Long> idProducts = List.of(1L);

        ProductEntity product = new ProductEntity();
        product.setProductName("Product A");
        product.setQuantity(2);
        product.setPrice(111);

        when(productRepository.findById(idProducts.get(0))).thenReturn(Optional.of(product));

        int total = productService.calculateTotalOldOrder(idProducts);

        assertEquals(222, total);
    }

    @Test
    public void getProductsTest() throws NoSuchFieldException, IllegalAccessException {
        OrderEntity orderEntity = new OrderEntity();
        ProductEntity productEntity = new ProductEntity("prodName", 0, 100, "note", orderEntity);
        Field field = ProductEntity.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(productEntity, 1L);

        ProductResponse productResponse = new ProductResponse(1L, "prodName", 100, "note");

        when(productRepository.findByOrderEntityIsNull()).thenReturn(List.of(productEntity));

        List<ProductResponse> responses = productService.getProducts();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        ProductResponse response = responses.get(0);
        assertEquals(productResponse.getId(), response.getId());
        assertEquals(productResponse.getProductName(), response.getProductName());
        assertEquals(productResponse.getPrice(), response.getPrice());
        assertEquals(productResponse.getNote(), response.getNote());
    }

    @Test
    void getProductsWithoutOrderTest() {
        productEntity = new ProductEntity();
        productEntity.setPrice(100);

        when(productRepository.findByOrderEntityIsNull()).thenReturn(List.of(productEntity));

        List<ProductEntity> result = productService.getProductsWithoutOrder();

        verify(productRepository).findByOrderEntityIsNull();

        assertEquals(productEntity.getPrice(), result.get(0).getPrice());
    }

    @Test
    void deleteProductTest() {
        Long productId = 1L;

        productService.deleteProduct(productId);

        verify(productRepository).deleteById(productId);
    }

    @Test
    void getByNameTest() {

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductName("ProductName");
        productEntity1.setOrderEntity(null);

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setProductName("ProductName");
        OrderEntity orderEntity1 = new OrderEntity();
        productEntity1.setOrderEntity(orderEntity1);

        ProductEntity productEntity3 = new ProductEntity();
        productEntity3.setProductName("ProductName1");

        when(productRepository.findByOrderEntityIsNull()).thenReturn(List.of(productEntity1, productEntity2, productEntity3));

        ProductEntity result = productService.getByName("ProductName");

        assertEquals("ProductName", result.getProductName());
        assertEquals(null, result.getOrderEntity());
    }

    @Test
    void getProductById() {
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity("prodName", 2, 100, "note", null);

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        ProductEntity result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(productEntity, result);
        verify(productRepository).findById(productId);
    }
}