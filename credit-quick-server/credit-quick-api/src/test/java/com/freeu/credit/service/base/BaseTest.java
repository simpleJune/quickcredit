package com.freeu.credit.service.base;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext*.xml" })
public class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    
    protected void printJson(Object obj) {
        String json = JSON.toJSONString(obj, true);
        System.out.println("=== result: \n"+ json);
    }
}
