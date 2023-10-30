package edu.whu.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.demo.domain.Product;
import edu.whu.demo.domain.ProductDTO;
import edu.whu.demo.domain.Result;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ControllerException;
import edu.whu.demo.exception.ProductAdminException;
import edu.whu.demo.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzw
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @ApiOperation("添加商品")
    @PutMapping("")
    public Product addProduct(@RequestBody Product product) throws ControllerException{
        Product result = null;
            result = productService.addProduct(product);
            return result;
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){

            productService.removeById(id);
    }

    @ApiOperation("修改商品信息")
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) throws ControllerException {

            productService.updateProduct(id, product);
            return product;
    }

    @ApiOperation("修改商品供应商")
    @PutMapping("/{id}/suppliers")
    public Product updateProductSuppliers(@PathVariable int id, @RequestBody List<Supplier> suppliers) throws ControllerException {

        productService.updateProductSuppliers(id,suppliers);
        return getProduct(id);
    }

    @ApiOperation("根据id查询商品")
    @GetMapping("/{id}")
    public Product getProduct(@ApiParam("商品id") @PathVariable int id) throws ControllerException {
        Product result = productService.getById(id);
        if(result == null){
            throw new ControllerException(ControllerException.FIND_ERROR, "不存在商品");
        }
        else {
            return result;
        }
    }

    /*@ApiOperation("根据条件查询商品")
    @GetMapping("")
    public ResponseEntity<Result<IPage<ProductDTO>>> findProduct(@ApiParam("商品名称")String name,
                                                                 @ApiParam("商品价格")Float price,
                                                                 @ApiParam("供应商名称")String supplierName,
                                                                 @ApiParam("页码")@RequestParam(defaultValue = "0")Integer pageNum,
                                                                 @ApiParam("每页记录数") @RequestParam(defaultValue = "10")Integer pageSize
                                                                 ){
        Map<String, Object> condition = new HashMap<>();if(name!=null) {
            condition.put("name","%"+name+"%");
        }
        if(price!=null) {
            condition.put("price",price);
        }

        if(supplierName!=null) {
            condition.put("supplierName",supplierName);
        }
        IPage<ProductDTO> result = productService.findProduct(condition, pageNum, pageSize);
        return ResponseEntity.ok(new Result<>(result,null));
    }*/





}

