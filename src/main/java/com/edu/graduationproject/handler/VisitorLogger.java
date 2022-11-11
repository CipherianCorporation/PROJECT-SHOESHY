package com.edu.graduationproject.handler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.edu.graduationproject.entity.Visitor;
import com.edu.graduationproject.service.VisitorService;
import com.edu.graduationproject.utils.HttpRequestResponseUtils;

@Component
public class VisitorLogger implements HandlerInterceptor {

    // source:
    // https://roytuts.com/how-to-log-online-user-activity-in-spring-boot-applications/

    @Autowired
    private VisitorService visitorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        final String ip = HttpRequestResponseUtils.getClientIpAddress();
        final String url = HttpRequestResponseUtils.getRequestUrl();
        final String page = HttpRequestResponseUtils.getRequestUri();
        final String refererPage = HttpRequestResponseUtils.getRefererPage();
        final String queryString = HttpRequestResponseUtils.getPageQueryString();
        final String userAgent = HttpRequestResponseUtils.getUserAgent();
        final String requestMethod = HttpRequestResponseUtils.getRequestMethod();
        final LocalDateTime timestamp = LocalDateTime.now();

        Visitor visitor = new Visitor();
        visitor.setUserInfo(HttpRequestResponseUtils.getLoggedInUser());
        visitor.setIp(ip);
        visitor.setMethod(requestMethod);
        visitor.setUrl(url);
        visitor.setPage(page);
        visitor.setQueryString(queryString);
        visitor.setRefererPage(refererPage);
        visitor.setUserAgent(userAgent);
        visitor.setLoggedTime(timestamp);

        List<Visitor> list = visitorService.findAllByIp(ip).orElse(null);
        if (list.isEmpty() || list == null) {
            visitor.setUniqueVisit(true);
        } else {
            visitor.setUniqueVisit(false);
        }

        visitorService.saveVisitorInfo(visitor);

        return true;
    }
}
