package edu.whu.demo.service;

import edu.whu.demo.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommodityService {

    // 创建线程安全的Map，模拟Commodity信息的存储
    private Map<Long, Commodity> goods = Collections.synchronizedMap(new HashMap<>());

    public Commodity getCommodity(long id) {
        return goods.get(id);
    }

    public Commodity addCommodity(Commodity commodity) {
        goods.put(commodity.getId(), commodity);
        return  commodity;
    }

    public void updateCommodity(long id, Commodity commodity) {
        Commodity commodity1 = goods.get(id);
        commodity1.setName(commodity.getName());
        commodity1.setPrice(commodity.getPrice());
        goods.put(id, commodity1);
    }

    public void deleteCommodity(long id){
        goods.remove(id);
    }


    public List<Commodity> findall() {
        List<Commodity> result = new ArrayList<>(goods.values());
        return result;
    }
}
