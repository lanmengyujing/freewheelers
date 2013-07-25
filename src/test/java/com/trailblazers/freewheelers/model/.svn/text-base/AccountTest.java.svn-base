package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class AccountTest {
    Address address;

    @Before
    public void setUp() {
        address = new Address("Street One", "Street Two", "City", "State", "Country", "Zip");
    }

    @Test
    public void testCreatingNewAccounts() throws Exception {
        Account account = new Account()
                .setAccount_name("Bob")
                .setPassword("password")
                .setEmailAddress("foo@bar.com")
                .setPhoneNumber("123443245")
                .setAddress(address);

        assertThat(account.getAccount_name(), is("Bob"));
        assertThat(account.getPassword(), is("password"));
        assertThat(account.getEmailAddress(), is("foo@bar.com"));
        assertThat(account.getPhoneNumber(), is("123443245"));
        assertEquals(account.getAddress(), address);
    }

    @Test
    public void testUpdatingAddress() throws Exception {
        address.setStreetOne("new street one");
        Account account = new Account()
                .setAccount_name("Bob")
                .setPassword("password")
                .setEmailAddress("foo@bar.com")
                .setPhoneNumber("123443245")
                .setAddress(address);

        assertEquals(account.getAddress(), address);
    }
}
