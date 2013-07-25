package com.trailblazers.freewheelers.model;

//public enum CreditCardPaymentStatus {
//    SUCCESS, UNAUTH, NET_ERR, RVK_CARD, UNEXCEPTED_RESPONSE, CONNECTION_ERROR
//}

public class CreditCardPaymentStatus{

    private static String SUCCESS = "SUCCESS";
    private static String UNAUTH = "UNAUTH";
    private static String NET_ERR = "NET_ERR";
    private static String RVK_CARD = "RVK_CARD";
    private static String UNEXPECTED_RESPONSE = "UNEXPECTED_RESPONSE";
    private static String CONNECTION_ERROR = "CONNECTION_ERROR";

    private String status;

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    private String transactionId;


    public CreditCardPaymentStatus(String status){
        this.status = status;
    }

    public CreditCardPaymentStatus(String status, String transactionId){
        this.status = status;
        this.transactionId = transactionId;
    }

    public boolean isSuccess(){
        return status.equals(SUCCESS);
    }

    public boolean isUnauth(){
        return status.equals(UNAUTH);
    }

    public boolean isNet_err(){
        return status.equals(NET_ERR);
    }

    public boolean isRvk_card(){
        return status.equals(RVK_CARD);
    }

    public boolean isUnexpected_response(){
        return status.equals(UNEXPECTED_RESPONSE);
    }


    public boolean isConnection_error(){
        return status.equals(CONNECTION_ERROR);
    }

    public static CreditCardPaymentStatus success(String transactionId){
        return new CreditCardPaymentStatus(SUCCESS,transactionId);
    }


    public static CreditCardPaymentStatus unauth(){
        return new CreditCardPaymentStatus(UNAUTH,null);
    }

    public static CreditCardPaymentStatus net_err(){
        return new CreditCardPaymentStatus(NET_ERR,null);
    }

    public static CreditCardPaymentStatus rvk_card(){
        return new CreditCardPaymentStatus(RVK_CARD,null);
    }

    public static CreditCardPaymentStatus unexpected_response(){
        return new CreditCardPaymentStatus(UNEXPECTED_RESPONSE,null);
    }

    public static CreditCardPaymentStatus connection_error(){
        return new CreditCardPaymentStatus(CONNECTION_ERROR,null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCardPaymentStatus)) return false;

        CreditCardPaymentStatus that = (CreditCardPaymentStatus) o;

        if (!status.equals(that.status)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}