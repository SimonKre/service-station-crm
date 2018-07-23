package pl.coderslab.carworkshop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogUserAgentAndStatistics implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        final String ua = ((HttpServletRequest)req).getHeader("User-Agent");
        System.out.println("User-Agent: " + ua);

        long startTime = System.currentTimeMillis();
        chain.doFilter(req, resp);
        long stopTime = System.currentTimeMillis();
        System.out.println("Request processing time: " + (stopTime - startTime));
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
