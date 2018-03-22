package com.codecool.web.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {

    @Test
    void testGetGreeting() {
        // given
        GreetingService service = new GreetingService();

        // when
        Greeting greeting = service.getGreeting();

        // then
        assertNotNull(greeting);
        assertNotNull(greeting.getText());
        assertEquals("Hi there traveller!", greeting.getText());
    }
}
