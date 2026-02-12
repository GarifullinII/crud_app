package ru.garifullin.crud.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);

        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(context);

        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcherServlet", dispatcherServlet);

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        registerHiddenFieldFilter(servletContext);
    }

    private void registerHiddenFieldFilter(ServletContext servletContext) {
        servletContext.addFilter(
                "hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()
        ).addMappingForUrlPatterns(null, true, "/*");
    }
}

