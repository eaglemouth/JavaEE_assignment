package edu.whu.demo.dao;

import edu.whu.demo.domain.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
