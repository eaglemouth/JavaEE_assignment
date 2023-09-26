package edu.whu;

import edu.whu.Main;
import org.junit.Assert;
import org.junit.Test;

// 测试类
public class TestMain {
    @Test
    public void testCreate() throws Exception {
        Assert.assertNotEquals(Main.createObject("MyClass"), null);
    }


    @Test
    public void testCheck() throws Exception {
        Assert.assertTrue(Main.checkAnnotation(Main.createObject("MyClass")));
    }
}
