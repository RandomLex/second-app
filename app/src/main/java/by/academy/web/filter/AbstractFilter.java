package by.academy.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

public abstract class AbstractFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Nothing to do
    }

    @Override
    public void destroy() {
        //Nothing to do
    }
}
