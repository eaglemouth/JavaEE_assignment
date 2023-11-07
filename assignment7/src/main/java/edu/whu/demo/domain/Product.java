package edu.whu.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiaxy
 * @since 2022-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String category;

    private String description;

    private String name;

    private Float price;

    @TableField(value = "product_type")
    private String productType;

    @TableField(value = "stock_quantity")
    private Float stockQuantity;


}
