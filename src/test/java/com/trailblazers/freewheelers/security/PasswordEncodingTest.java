package com.trailblazers.freewheelers.security;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PasswordEncodingTest {

    private PasswordEncoding passwordEncoding;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setUp() {
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        passwordEncoding = new PasswordEncoding(bCryptPasswordEncoder);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encrypted_password");
    }

    @Test
    public void shouldReturnEmptyStringWhenProvidedWithAnEmptyString() {

        assertThat("", is(passwordEncoding.encode("")));

    }

    @Test
    public void shouldReturnNullWhenProvidedWithNull() {

        assertNull(passwordEncoding.encode(null));

    }

    @Test
    public void shouldReturnAnNonEmptyStringWhenProvidedWithNonEmptyString() {

        String encodedPassword = passwordEncoding.encode("password");

        verify(bCryptPasswordEncoder).encode("password");
        assertThat(encodedPassword.length(), greaterThan(0));

    }
}
