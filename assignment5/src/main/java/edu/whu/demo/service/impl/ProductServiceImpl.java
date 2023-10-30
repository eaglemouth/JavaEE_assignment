package edu.whu.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.demo.domain.Product;
import edu.whu.demo.dao.ProductDao;
import edu.whu.demo.domain.ProductDTO;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import edu.whu.demo.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2023-10-24
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService {

    @Transactional
    @Override
    public Product addProduct(Product product) throws ProductAdminException {
        try {
            this.baseMapper.insert(product);
            return product;
        }catch (Exception e){
            throw new ProductAdminException("修改产品失败" + e.getMessage());
        }

    }

    @Transactional(rollbackFor = ProductAdminException.class)
    @Override
    public void updateProduct(int id, Product product) throws ProductAdminException{
        if(this.baseMapper.selectById(id) == null){
            throw new ProductAdminException("产品" + id + "不存在");
        }

        try {
            this.baseMapper.updateById(product);
        }catch (Exception e){
            throw new ProductAdminException("修改产品失败" + e.getMessage());
        }
    }

    @Override
    public IPage<ProductDTO> findProduct(Map<String, Object> condition, Integer pageNum, Integer pageSize) {
        Page<ProductDTO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ProductDTO> qw = new QueryWrapper<>();
        qw.like(condition.containsKey("name"), "p.name", condition.get("name"));
        qw.le(condition.containsKey("price"), "p.price", condition.get("price"));
        if(condition.containsKey("supplierName")){
            qw.eq("s.name", condition.get("supplierName"));
            this.baseMapper.findProductsBySupplier(page, qw); //多表查询
        }else
        {
            this.baseMapper.findProducts(page, qw); //单表查询
        }
        return page;
    }

    @Transactional(rollbackFor = ProductAdminException.class)
    @Override
    public void deleteProduct(int id) throws ProductAdminException{
        try {
            this.baseMapper.deleteSuppliersOfProduct(id);
            this.baseMapper.deleteById(id);
        } catch (Exception e) {
            throw new ProductAdminException("删除产品失败：" + e.getMessage());
        }
    }

    @Transactional(rollbackFor = ProductAdminException.class)
    @Override
    public void updateProductSuppliers(long id, List<Supplier> suppliers) throws ProductAdminException {
        if (this.baseMapper.selectById(id) == null) {
            throw new ProductAdminException("产品" + id + "不存在");
        }

        try {
            this.baseMapper.deleteSuppliersOfProduct(id);
            for (Supplier supplier : suppliers) {
                this.baseMapper.insertProductSupplier(id, supplier.getId());
            }
        } catch (Exception e) {
            throw new ProductAdminException("修改产品失败：" + e.getMessage());
        }
    }
}
