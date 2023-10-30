package edu.whu.demo.aspect;

import edu.whu.demo.controller.ProductController;
import edu.whu.demo.domain.Product;
import edu.whu.demo.exception.ControllerException;
import edu.whu.demo.exception.ProductAdminException;
import edu.whu.demo.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@SpringBootTest
@Transactional
public class AspectTest {

    @Autowired
    ProductController productController;

    @Autowired
    CountAscept countAscept;
    @Test
    public void testCountApi() throws ControllerException {
        productController.getProduct(1);
//        productController.deleteProduct(1);
//        productController.findProduct("Product B", 28.00F, "supplier Y", null, null);
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();
        product.setId(1);
        product.setName("Product D");
        product.setDescription("test");
        product.setPrice(new BigDecimal(34));
        product1.setId(36);
        product1.setName("Product E");
        product1.setDescription("test");
        product1.setPrice(new BigDecimal(34));
        product2.setId(37);
        product2.setName("Product F");
        product2.setDescription("test");
        product2.setPrice(new BigDecimal(34));

        productController.addProduct(product);
        productController.addProduct(product1);
        productController.addProduct(product2);

        productController.deleteProduct(33);
        productController.deleteProduct(36);
        productController.deleteProduct(37);


        for (Map.Entry<String, ApiStatistics> entry : countAscept.getStatisticsMap().entrySet()) {
            String api = entry.getKey();
            ApiStatistics stats = entry.getValue();
            System.out.println("API: " + api);
            System.out.println("调用次数: " + stats.getCallCount());
            System.out.println("异常次数: " + stats.getErrorCount());
            System.out.println("平均响应时间: " + (stats.getTotalResponseTime() / stats.getCallCount()));
            System.out.println("最短响应时间: " + stats.getMinResponseTime());
            System.out.println("最长响应时间: " + stats.getMaxResponseTime());
        }
    }
}
