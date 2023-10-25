package edu.whu.demo.service;

import edu.whu.demo.domain.Product;
import edu.whu.demo.domain.Product_supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProduct_supplierService productSupplierService;


    @Test
    public void testFind(){
        Product result = productService.getById(1);

        assertNotNull(result);

    }

    @Test
    public void testAdd(){
        Product product = new Product();
        product.setName("pencil");
        product.setPrice(new BigDecimal("3.99"));
        product.setDescription("expensive");

        boolean isSuccess = productService.save(product);
        assertNotNull(isSuccess);
    }

    @Test
    public void testUpdate(){
        int productID = 1;
        Product existingProduct = productService.getById(productID);
        assertNotNull(existingProduct);
        existingProduct.setDescription("Updated Product Description");

        boolean isSuccess = productService.updateById(existingProduct);
        assertNotNull(isSuccess);

        Product updatedProduct = productService.getById(productID);
        assertEquals("Updated Product Description", updatedProduct.getDescription());
    }

    @Test
    public void testDelete(){
        int productID = 2;
        productSupplierService.removeById(productID);
        // 删除产品
        boolean isSuccess = productService.removeById(productID);

        // 验证删除是否成功
        assertTrue(isSuccess);

        // 再次查询产品，应该为空
        Product deletedProduct = productService.getById(productID);
        assertNull(deletedProduct);
    }

}
