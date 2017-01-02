package net.kursy.bobko.holder;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class HolderInit {

    private static HolderInit instance;

    public static HolderInit getInstance() {
        if (instance == null){
            instance = new HolderInit();
        }
        return instance;
    }

    public Holder initParamsHolder(HttpServletRequest request){
        Holder holder = new Holder();
        String currentAttributesName;
        Map<String, Object> requestAttributes = new HashMap<String, Object>();
        Enumeration attributesNames = request.getAttributeNames();
        while (attributesNames.hasMoreElements()) {
            currentAttributesName = attributesNames.nextElement().toString();
            requestAttributes.put(currentAttributesName, request.getAttribute(currentAttributesName));
        }
            holder.setRequestAttributes(requestAttributes);


        Map<String, Object> sessionAttributes = new HashMap<String, Object>();
        attributesNames = request.getSession().getAttributeNames();
        while (attributesNames.hasMoreElements()) {
            currentAttributesName = attributesNames.nextElement().toString();
            sessionAttributes.put(currentAttributesName, request.getSession().getAttribute(currentAttributesName));
        }
            holder.setSessionAttributes(sessionAttributes);


        Map<String, String> requestParams = new HashMap<String, String>();
        for (Enumeration paramsNames = request.getParameterNames();
             paramsNames.hasMoreElements(); ) {
            currentAttributesName = paramsNames.nextElement().toString();
            String[] currentAttribute = request.getParameterValues(currentAttributesName);
            requestParams.put(currentAttributesName, currentAttribute[0]);
        }
            holder.setRequestParameters(requestParams);

        return holder;
    }

    public void update(Holder holder, HttpServletRequest request){
        if (holder.getSessionAttributes().get("invalidate") != null){
            request.getSession().invalidate();
            return;
        }
        Set requestAttributesName = holder.getRequestAttributes().keySet();
        Iterator iterator = requestAttributesName.iterator();
        String currAttr;
        while (iterator.hasNext()){
            currAttr = (String) iterator.next();
            request.setAttribute(currAttr, holder.getRequestAttributes().get(currAttr));
        }
        Set sessionAttributesName = holder.getSessionAttributes().keySet();
        iterator = sessionAttributesName.iterator();
        while (iterator.hasNext()){
            currAttr = (String) iterator.next();
            request.getSession().setAttribute(currAttr, holder.getSessionAttributes().get(currAttr));
        }
    }

}
