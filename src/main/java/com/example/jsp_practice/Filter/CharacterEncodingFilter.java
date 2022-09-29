package com.example.jsp_practice.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //System.out.println("before filter");

        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        //System.out.println("after filter");

    }
}
