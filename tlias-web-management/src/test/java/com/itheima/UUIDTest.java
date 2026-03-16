package com.itheima;

import org.junit.jupiter.api.Test;

public class UUIDTest {

    @Test
    public void testUuid() {
        for (int i = 0; i < 10; i++) {
            System.out.println(java.util.UUID.randomUUID().toString());
        }
    }
}
