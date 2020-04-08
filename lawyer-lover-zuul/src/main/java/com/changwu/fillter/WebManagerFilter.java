package com.changwu.fillter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
public class WebManagerFilter extends ZuulFilter {

    /**
     * 指定过滤器的类型
     * pre ：可以在请求被路由之前调用
     * route ：在路由请求时候被调用
     * post ：在route和error过滤器之后被调用
     * error ：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 指定过滤器的优先级, 0表示优先执行, 因为我们可以写很多个过滤器
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启, true表示开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器执行逻辑, 返回任意Object类型的值,都表示放行,包括null
     * <p>
     * 如果不想往后继续执行了,就使用 setSendZullResponse(false)
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        currentContext.getResponse().setContentType("UTF-8");
        String ip = getIpAddr(request);
        // todo ip的限制
        //if (ip.equals("0:0:0:0:0:0:0:1")){
           // resultError(currentContext,"Due to your malicious access, the system refused to serve you");
        //}
        return null;
    }

    /**
     * 获取ip地址
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 响应给客户端消息
     * @param ctx
     * @param errorMsg
     */
    private void resultError(RequestContext ctx, String errorMsg) {
        ctx.setResponseStatusCode(401);
        // setSendZuulResponse=false , zuul将不会再将当前请求转发到后端的其他服务中
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);
    }

}