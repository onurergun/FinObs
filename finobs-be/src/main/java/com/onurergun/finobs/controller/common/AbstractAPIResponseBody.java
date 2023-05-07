package com.onurergun.finobs.controller.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAPIResponseBody {

    private Map<String, String> requestData;

    private long timestamp;
    private String url;

    private boolean successful;

    private String message;

    public AbstractAPIResponseBody(String url) {
        requestData = new HashMap<>();
        timestamp = new Date().getTime();

        if (url == null || url.isEmpty() || url.isBlank())
            throw new IllegalArgumentException("URL argument has to have value");

        this.url = url;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUrl() {
        return url;
    }

    public boolean isSuccessful() {
        return successful;
    }

    protected void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Map<String, String> getRequestData() {
        return requestData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean addUpdateRequestDataParam(String key, String value) {
        if (key == null || key.isBlank() || key.isEmpty())
            return false;

        requestData.put(key, value);
        return true;
    }

    public boolean removeDataParam(String key) {
        return requestData.remove(key) != null;
    }
}
