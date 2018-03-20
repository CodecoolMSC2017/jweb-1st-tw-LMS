package com.codecool.web.listener;

import com.codecool.web.service.DataContainer;
import com.codecool.web.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext cntxt = sce.getServletContext();
        DataContainer dataContainer = new DataContainer();
        UserService userService = new UserService();

        cntxt.setAttribute("dataContainer", dataContainer);
        cntxt.setAttribute("userService",userService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
