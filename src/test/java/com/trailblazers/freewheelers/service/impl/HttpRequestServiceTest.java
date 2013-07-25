package com.trailblazers.freewheelers.service.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HttpRequestServiceTest {

    private HttpRequestService httpRequestService;
    @Mock
    private HttpResponse mockedHttpResponse;
    @Mock
    private HttpEntity mockedHttpEntity;

    @Before
    public void setUp() {
        initMocks(this);
        httpRequestService = new HttpRequestService();
        httpRequestService.setGatewayUrl("http://ops.university.thoughtworks.com:4567/authorize");
    }

    @Test
    public void shouldCreatePostRequest() throws Exception {
        HttpPost postRequest = httpRequestService.createXmlPostRequest("somexml");

        assertThat(postRequest.getURI(), is(new URI("http://ops.university.thoughtworks.com:4567/authorize")));
        assertThat(postRequest.getEntity().getContentType().getValue(), is("text/xml; charset=ISO-8859-1"));
        assertThat(new Scanner(postRequest.getEntity().getContent()).next(), is("somexml"));
    }

    @Test
    public void shouldGetResponseContent() throws Exception {
        when(mockedHttpResponse.getEntity()).thenReturn(mockedHttpEntity);
        when(mockedHttpEntity.getContent()).thenReturn(
                new ByteArrayInputStream("some String".getBytes()));

        String responseContent = httpRequestService.getResponseContent(mockedHttpResponse);

        assertThat(responseContent, is("some String"));
    }
}
