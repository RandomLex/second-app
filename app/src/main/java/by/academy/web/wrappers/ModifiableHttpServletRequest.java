package by.academy.web.wrappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModifiableHttpServletRequest extends HttpServletRequestWrapper {

    private ThreadLocal<Map<String, String[]>> parameterMap = new ThreadLocal<>();

    /**
     * Constructs a request object wrapping the given request.
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public ModifiableHttpServletRequest(HttpServletRequest request) {
        super(request);
        parameterMap.set(new HashMap<>(request.getParameterMap()));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap.get();
    }

    public void setParameter(String name, String value) {
        setParameter(name, value, true);
    }

    public void setParameter(String name, String value, boolean override) {
        Map<String, String[]> map = parameterMap.get();
        if (override) {
            map.put(name, new String[] {value});
        } else {
            String[] oldParams = map.get(name);
            String[] newParams = Arrays.copyOf(oldParams, oldParams.length + 1);
            map.put(name, newParams);
        }
    }

    @Override
    public String getParameter(String name) {
        return parameterMap.get().get(name)[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return parameterMap.get().get(name);
    }

    public Set<String> getParameterNamesAsSet() {
        return parameterMap.get().keySet();
    }
}
