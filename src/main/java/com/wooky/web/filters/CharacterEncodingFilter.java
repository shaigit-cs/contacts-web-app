package com.wooky.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/handler")
public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        //nothing to initialize
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //nothing to destroy
    }
}
