package com.onurergun.finobsbe.common;

public class APIResponseBody extends AbstractAPIResponseBody {

    private Object responseData;

    public APIResponseBody(String url) {
        super(url);
        setSuccessful(true);
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
