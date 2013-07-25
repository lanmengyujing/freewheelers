package com.trailblazers.freewheelers.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpRequestService {

    private String gatewayUrl;
    private HttpClient httpClient;

    public HttpRequestService() {
        httpClient = new DefaultHttpClient();
    }

    public String sendRequestToCreditCardGateway(String xmlRequest) throws IOException, URISyntaxException {
        return getResponseContent(httpClient.execute(createXmlPostRequest(xmlRequest)));
    }

    protected HttpPost createXmlPostRequest(String xmlRequest) throws URISyntaxException {
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(new URI(gatewayUrl));
        httpPost.setEntity(new StringEntity(xmlRequest, ContentType.TEXT_XML));
        return httpPost;
    }

    protected String getResponseContent(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int count;

        while ((count = inputStream.read(data, 0, 1024)) != -1) {
            outStream.write(data, 0, count);
        }
        String responseString = new String(outStream.toByteArray());

        return responseString;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }
}
