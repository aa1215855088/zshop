import com.hnguigu.zshop.domain.Order;
import com.hnguigu.zshop.service.OrderItemServer;
import com.hnguigu.zshop.service.OrderServer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-18 09:19
 **/
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class Test {

    @Autowired
    private OrderServer orderServer;

    @org.junit.Test
    public void test1() {
        List<Order> order = orderServer.getOrderByUserId(3);
        for (Order order1 : order) {
            System.out.println(order1.getOrderItem());
        }
    }
}
