package com.onurergun.finobsbe.controller.common;

import java.util.HashMap;
import java.util.Map;

public class APIResponseBodyError extends AbstractAPIResponseBody {

    private Map<String, String> errors;

    public APIResponseBodyError(String url) {
        super(url);
        errors = new HashMap<>();
        setSuccessful(false);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public boolean addUpdateErrorParam(String key, String value) {
        if (key == null || key.isBlank() || key.isEmpty())
            return false;

        errors.put(key, value);
        return true;
    }

    public boolean removeErrorParam(String key) {
        return errors.remove(key) != null;
    }
}
