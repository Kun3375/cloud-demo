//package com.kun.demo.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Objects;
//
///**
// * @author CaoZiye
// * @version 1.0 2018/6/10 19:43
// */
//@Component
//public class AccessFilter extends ZuulFilter {
//
//    private static final Logger log = LoggerFactory.getLogger(AccessFilter.class);
//
//    @Override
//    public String filterType() {
//        // 什么时候过滤，pre：路由前，router：路由时，post：路由完成，error：错误
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        // 决定多个 filter 的顺序
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        // 决定该 filter 是否生效
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        // 过滤逻辑
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        log.info("access filter...");
//
//        String deny = request.getParameter("deny");
//        if (Objects.equals(deny, "true")) {
//            log.info("access deny");
//
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//        }
//
//        log.info("access pass");
//        return null;
//    }
//}
