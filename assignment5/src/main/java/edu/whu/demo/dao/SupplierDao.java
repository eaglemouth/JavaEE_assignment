package edu.whu.demo.dao;

import edu.whu.demo.domain.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzw
 * @since 2023-10-24
 */
@Mapper
public interface SupplierDao extends BaseMapper<Supplier> {
    @Select("select supplier.* from supplier, product_supplier " +
            "where supplier.id = product_supplier.supplier_id " +
            "and product_supplier.product_id = #{productId}")
    public List<Supplier> findSuppliersByProduct(@Param("productId")Long productId);
}
