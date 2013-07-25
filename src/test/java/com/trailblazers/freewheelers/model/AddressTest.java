package com.trailblazers.freewheelers.model;


import org.junit.Test;

import static org.testng.Assert.assertNotNull;

public class AddressTest {

    @Test
    public void shouldNotBeNullWhenItIsCreated() {
        assertNotNull(new Address("One", "Two", "City", "State", "Country", "94103"));
    }
}
