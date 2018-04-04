package com.codecool.web.listener;

import com.codecool.web.service.CourseServiceImpl;
import com.codecool.web.service.DataContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext cntxt = sce.getServletContext();
        DataContainer dataContainer = DataContainer.getInstance();
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl();

        cntxt.setAttribute("dataContainer", dataContainer);
        cntxt.setAttribute("courseServiceImpl", courseServiceImpl);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
