package com.cokroktoupadek.beer_ap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class BeerApApplicationTests {

    @Test
    void contextLoads() {
    }

}
