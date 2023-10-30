package edu.whu.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import edu.whu.demo.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.demo.domain.ProductDTO;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzw
 * @since 2023-10-24
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {

    @Insert("insert into product_supplier (product_id,supplier_id) values(#{productId},#{supplierId})")
    public void insertProductSupplier(long productId,long supplierId);

    @Delete("delete from product_supplier where product_id = #{productId}")
    public void deleteSuppliersOfProduct(long productId);

    /**
     * 查询条件包括supplier信息时，需要多表联合查询
     * @param page
     * @param wrapper
     * @return
     */
    @Select("select distinct p.* from product p" +
            " left join product_supplier ps on p.id=ps.product_id " +
            " left join supplier s on s.id=ps.supplier_id " +
            " ${ew.customSqlSegment}") //由QueryWrapper转换
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "supplierList", column = "id",
                    many = @Many(select = "edu.whu.demo.dao.SupplierDao.findSuppliersByProduct"))})
    public IPage<ProductDTO> findProductsBySupplier(IPage<ProductDTO> page,
                                                    @Param(Constants.WRAPPER) QueryWrapper<ProductDTO> wrapper);

    /**
     * 查询条件只有product信息时，进行单表查询
     * @param page
     * @param wrapper
     * @return
     */
    @Select("select p.* from product p ${ew.customSqlSegment}")// ${ew.customSqlSegment}报错无伤大雅
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "supplierList", column = "id",
                    many = @Many(select = "edu.whu.demo.dao.SupplierDao.findSuppliersByProduct"))})
    public IPage<ProductDTO> findProducts(IPage<ProductDTO> page,
                                          @Param(Constants.WRAPPER) QueryWrapper<ProductDTO> wrapper);
}
