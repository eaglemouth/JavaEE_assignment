package edu.whu.demo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public MybatisPlusInterceptor myInterceptor(){
        //1.定义拦截器
        MybatisPlusInterceptor myInterceptor = new MybatisPlusInterceptor();
        //2.添加具体的拦截器
        myInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return myInterceptor;
    }
}
