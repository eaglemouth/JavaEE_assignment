package edu.whu.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.demo.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.demo.domain.ProductDTO;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzw
 * @since 2023-10-24
 */
public interface IProductService extends IService<Product> {
    Product addProduct(Product product) throws ProductAdminException;

    void updateProduct(int id, Product product) throws ProductAdminException;

    IPage<ProductDTO> findProduct(Map<String, Object> condition, Integer pageNum, Integer pageSize);

    @Transactional(rollbackFor = ProductAdminException.class)
    void deleteProduct(int id) throws ProductAdminException;

    void updateProductSuppliers(long id, List<Supplier> suppliers) throws ProductAdminException;
}
