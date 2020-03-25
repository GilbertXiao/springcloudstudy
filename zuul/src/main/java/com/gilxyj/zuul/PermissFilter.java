package com.gilxyj.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-03-13 00:29
 **/
@Component
public class PermissFilter extends ZuulFilter {

    /**
     * 过滤器类型，权限判断一般是pre
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的优先级
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 核心的业务逻辑写在这里
     * 这个方法虽然有返回值，但是这个返回值目前无所谓
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();//获取当前请求
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(!"gilxyj".equals(username)||!"123".equals(password)){
            //如果请求条件不满足的话，执行这里
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            currentContext.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
            currentContext.setResponseBody("非法访问！");
        }

        return null;
    }
}
