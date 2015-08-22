package ilielucian.demo.bank.application.config;

import ilielucian.demo.bank.authorization.config.SpringSecurityConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Class for initializing the Web Application. Replaces the <code>web.xml</code> file.
 * <p>
 * Created by Lucian Ilie on 13-Aug-15.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.setConfigLocation("ilielucian.demo.config");

        ctx.register(WebAppConfig.class);
        ctx.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(ctx));

        ServletRegistration.Dynamic servlet = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
