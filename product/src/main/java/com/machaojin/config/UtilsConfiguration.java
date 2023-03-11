package com.machaojin.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import io.lettuce.core.dynamic.annotation.Value;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.serializer.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.time.Duration;

/**
 * @author machaojin
 * @version 1.0
 * @time 2022/11/24 18:34 星期四
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(CacheProperties.class)
public class UtilsConfiguration {
    /**
     * mybtis-plus分页插件的配置
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor interceptor = new PaginationInnerInterceptor();
        //默认1000条最大
        interceptor.setMaxLimit(1000L);
        mybatisPlusInterceptor.addInnerInterceptor(interceptor);
        return mybatisPlusInterceptor;
    }

    /**
     * redis分布式锁
     * @return
     * @throws IOException 异常
     */
    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() throws IOException {
        Config config = new Config();
        //rediss://--是安全模式
        config.useSingleServer().setAddress("redis://101.42.149.76:6379");
        return Redisson.create(config);
    }

    /**
     * 要想开启缓存，就需要添加@EnableCache注解在配置类上，即可添加缓存
     * 配置redis缓存的各种相关配置
     *  1、在原来的依赖中，所有的有关redis的配置都在RedisCacheConfiguration中,要想覆盖配置，就需要重写相关的配置，重写RedisCacheConfiguration
     *  2、重写redis缓存的值的生成规则，使用fastJSON的JSON转换器
     * @param cacheProperties 有关redis的所有配置，
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties){
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        if (redisProperties.getTimeToLive() != null) {
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            redisCacheConfiguration = redisCacheConfiguration.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            redisCacheConfiguration = redisCacheConfiguration.disableKeyPrefix();
        }
        return redisCacheConfiguration;
    }

}
