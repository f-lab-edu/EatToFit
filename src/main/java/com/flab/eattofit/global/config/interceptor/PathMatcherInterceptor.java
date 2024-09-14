package com.flab.eattofit.global.config.interceptor;

import com.flab.eattofit.global.config.interceptor.support.PathContainer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PathMatcherInterceptor implements HandlerInterceptor {

    private final HandlerInterceptor handlerInterceptor;
    private final PathContainer pathContainer;

    public PathMatcherInterceptor(final HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
        this.pathContainer = new PathContainer();
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (pathContainer.isNotIncludedPath(request.getServletPath(), request.getMethod())) {
            return true;
        }
        return handlerInterceptor.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response,
                           final Object handler,
                           final @Nullable ModelAndView modelAndView) throws Exception {
        if (!pathContainer.isNotIncludedPath(request.getServletPath(), request.getMethod())) {
            handlerInterceptor.postHandle(request, response, handler, modelAndView);
        }
    }

    public PathMatcherInterceptor addPathPatterns(final String pathPattern, final HttpMethod... httpMethod) {
        pathContainer.addIncludePatterns(pathPattern, httpMethod);
        return this;
    }

    public PathMatcherInterceptor excludePathPatterns(final String pathPattern, final HttpMethod... pathMethod) {
        pathContainer.addExcludePatterns(pathPattern, pathMethod);
        return this;
    }
}
