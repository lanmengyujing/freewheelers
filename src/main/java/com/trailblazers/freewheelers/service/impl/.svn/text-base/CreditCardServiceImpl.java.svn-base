package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.model.CreditCardPayment;
import com.trailblazers.freewheelers.model.CreditCardPaymentStatus;
import com.trailblazers.freewheelers.service.CreditCardService;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.slf4j.LoggerFactory.getLogger;

public class CreditCardServiceImpl implements CreditCardService {

    private Logger logger = getLogger(this.getClass());
    private String extractResponseStatusPattern;

   
    @Autowired
    private HttpRequestService httpRequestService;

    @Override
    public ServiceResult<CreditCardPaymentStatus> pay(final CreditCardPayment creditCardPayment) {

        String responseContent = null;
        CreditCardPaymentStatus creditCardPaymentStatus = null;
        HashMap<String, String> errors = new HashMap<String, String>();
        try {
            responseContent = httpRequestService.sendRequestToCreditCardGateway(creditCardPayment.asXML());
            creditCardPaymentStatus = getCreditCardPaymentStatus(responseContent);
        } catch (IOException e) {
            logger.error("Error when send credit card payment", e);
            errors.put("payment","error io exception / uri syntax exception");

        } catch (URISyntaxException e) {
            logger.error("Error when send credit card payment", e);
            errors.put("payment","error io exception / uri syntax exception");
        }
        

        return new ServiceResult<CreditCardPaymentStatus>(errors,creditCardPaymentStatus);
    }

    protected CreditCardPaymentStatus getCreditCardPaymentStatus(final String responseContent) {
        Pattern pattern = Pattern.compile(extractResponseStatusPattern);
        Matcher matcher = pattern.matcher(responseContent);
        if (matcher.find()) {
            CreditCardPaymentStatus creditCardPaymentStatus = new CreditCardPaymentStatus(matcher.group(1));

            if (creditCardPaymentStatus.isSuccess()) creditCardPaymentStatus.setTransactionId(matcher.group(2));

            return creditCardPaymentStatus;
        } else {
            return CreditCardPaymentStatus.unexpected_response();
        }
    }

    protected String getCreditCardPaymentTransactionId(final String responseContent) {
        Pattern pattern = Pattern.compile(extractResponseStatusPattern);
        Matcher matcher = pattern.matcher(responseContent);
        matcher.find();
        return matcher.group(2);
    }

    public void setExtractResponseStatusPattern(final String extractResponseStatusPattern) {
        this.extractResponseStatusPattern = extractResponseStatusPattern;
    }

    public void setHttpRequestService(final HttpRequestService httpRequestService) {
        this.httpRequestService = httpRequestService;
    }

   
}
