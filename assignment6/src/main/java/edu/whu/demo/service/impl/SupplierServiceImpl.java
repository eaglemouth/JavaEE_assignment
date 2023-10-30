package edu.whu.demo.service.impl;

import edu.whu.demo.domain.Supplier;
import edu.whu.demo.dao.SupplierDao;
import edu.whu.demo.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2023-10-24
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, Supplier> implements ISupplierService {

}
