package edu.whu.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.demo.dao.ProductDao;
import edu.whu.demo.dao.SupplierDao;
import edu.whu.demo.domain.Product;
import edu.whu.demo.domain.ProductDTO;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private IProductService productService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    ProductDao productDao;

    @Autowired
    SupplierDao supplierDao;


    @Test
    public void testFind(){
        Product result = productService.getById(1);

        assertNotNull(result);

    }

    @Test
    public void testFindProduct(){
        Map<String,Object> condition=new HashMap<>();
        IPage<ProductDTO> result = productService.findProduct(condition, 0, 3);
        assertEquals(3,result.getRecords().size());

        //添加动态查询条件
        condition.put("name","Product");
        condition.put("price",new BigDecimal(288.00));
        result = productService.findProduct(condition, 0,3);
        assertEquals(2,result.getRecords().size());

        //使用跨表属性查询(supplierName在supplier表中)
        condition.put("supplierName","Supplier Y");
        result = productService.findProduct(condition, 0,3);
        assertEquals(2,result.getTotal());
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
    public void testAddProduct() throws ProductAdminException {
        Supplier supplier = new Supplier();
        supplier.setName("A");
        supplierDao.insert(supplier);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("D");
        productDTO.setPrice(new BigDecimal(5.66));
        productDTO.getSupplierList().add(supplier);
        Product product = productService.addProduct(productDTO);

        assertNotNull(product);
        int id = product.getId();
        Product product1 = productDao.selectById(id);
        assertNotNull(product1);

        productService.deleteProduct(id);
        assertNull(productDao.selectById(id));

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
    public void testUpdateProduct() throws ProductAdminException {
        Supplier supplier=new Supplier();
        supplier.setName("C公司");
        supplierDao.insert(supplier);
        Product product=new Product();
        product.setName("IPhone 18");
        product.setPrice(new BigDecimal(3400));
        Product product2 = productService.addProduct(product);

        //使用非法的id修改
        assertThrows(ProductAdminException.class, () -> {
            product2.setName("Xiaomi 3000");
            productService.updateProduct(-1,product);
        });

        //修改产品名称，去掉商品的供应商
        product2.setName("Xiaomi 3000");
        productService.updateProduct(product.getId(),product);
        //验证结果
        Product product3 = productDao.selectById(product.getId());
        assertNotNull(product3);
        assertEquals("Xiaomi 3000",product3.getName());
    }

}
