package edu.whu.demo.service.impl;

import edu.whu.demo.domain.Product_supplier;
import edu.whu.demo.dao.Product_supplierDao;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.service.IProduct_supplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2023-10-24
 */
@Service
public class Product_supplierServiceImpl extends ServiceImpl<Product_supplierDao, Product_supplier> implements IProduct_supplierService {

}
