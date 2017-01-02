package net.kursy.bobko.holder;


import java.util.HashMap;
import java.util.Map;

public class Holder {

    private Map<String, Object> sessionAttributes = new HashMap<String, Object>();
    private Map<String, String> requestParameters = new HashMap<String, String>();
    private Map<String, Object> requestAttributes = new HashMap<String, Object>();

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(Map<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }
}
