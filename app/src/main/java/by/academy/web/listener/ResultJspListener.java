package by.academy.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class ResultJspListener implements HttpSessionAttributeListener {
    Logger log = LoggerFactory.getLogger(ResultJspListener.class);
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        anyEventHandler(event, "added");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        anyEventHandler(event, "removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        anyEventHandler(event, "replaced");
    }

    private void anyEventHandler(HttpSessionBindingEvent event, String action) {
        if ("teacher".equals(event.getName())) {
            log.info("User {} is {}", event.getValue(), action);
        }
    }
}
