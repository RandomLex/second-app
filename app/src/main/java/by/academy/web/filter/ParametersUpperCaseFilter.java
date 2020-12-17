package by.academy.web.filter;

import by.academy.web.wrappers.ModifiableHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "UpperCaseFilter", urlPatterns = "/*")
public class ParametersUpperCaseFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ModifiableHttpServletRequest wrappedRequest = new ModifiableHttpServletRequest((HttpServletRequest) request);
        wrappedRequest.getParameterNamesAsSet()
                .forEach(s -> changeParameterToUpper(wrappedRequest, s));
        chain.doFilter(wrappedRequest, response);
    }

    private void changeParameterToUpper(ModifiableHttpServletRequest wrappedRequest, String s) {
        wrappedRequest.setParameter(s, wrappedRequest.getParameter(s).toUpperCase(Locale.ROOT));
    }

}
