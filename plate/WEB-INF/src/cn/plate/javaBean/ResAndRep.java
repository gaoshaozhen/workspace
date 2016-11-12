package cn.plate.javaBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResAndRep
{
    HttpServletRequest request;
    HttpServletResponse response;

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }
}
