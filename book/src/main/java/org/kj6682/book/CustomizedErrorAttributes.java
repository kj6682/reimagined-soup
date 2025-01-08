package org.kj6682.book;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class CustomizedErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions errorAttributeOptions){
        var errorAttributes = super.getErrorAttributes(webRequest, errorAttributeOptions);
        errorAttributes.put("parameters", webRequest.getParameterMap());
        return errorAttributes;
    }
}
