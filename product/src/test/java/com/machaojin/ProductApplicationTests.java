package com.machaojin;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTests {
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test(){
        System.out.println(redissonClient);
    }

}
