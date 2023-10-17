package edu.whu.demo.controller;

import edu.whu.demo.entity.Commodity;
import edu.whu.demo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commodity")
public class Controller {
    @Autowired
    CommodityService commodityService;

    @GetMapping()
    public ResponseEntity<List<Commodity>> getAll(){
        List<Commodity> result = commodityService.findall();
        return ResponseEntity.ok(result);
    }

    // 根据id查询
    @GetMapping("/{id}")
    public ResponseEntity<Commodity> getCommodity(@PathVariable long id){
        Commodity result = commodityService.getCommodity(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    // 添加
    @PostMapping()
    public ResponseEntity<Commodity> addCommodity(@RequestBody Commodity commodity){
        Commodity result = commodityService.addCommodity(commodity);
        return  ResponseEntity.ok(result);
    }

    // 修改
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCommodity(@PathVariable long id, @RequestBody Commodity commodity){
        commodityService.updateCommodity(id, commodity);
        return ResponseEntity.ok().build();
    }

    // 删除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommodity(@PathVariable long id){
        commodityService.deleteCommodity(id);
        return  ResponseEntity.ok().build();
    }

}
