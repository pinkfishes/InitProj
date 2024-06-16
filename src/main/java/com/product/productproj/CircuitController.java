package com.product.productproj;

import com.product.productproj.helper.CircuitConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class CircuitController {
    @CircuitConfig(timeout = 100L,failCount = -1)
    @PostMapping(value = "/test")
    public void test(){
        try {
            Random random = new Random();
            int time = random.nextInt(200);
            System.out.println(time + "ms");
            Thread.sleep(time);
            System.out.println("the end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
