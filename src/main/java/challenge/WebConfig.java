package challenge;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by xiaoboyu on 5/21/17.
 */
@Configuration
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * View Resolver: when access: localhost:8080/, it will load index.html page
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/static/index.html");

    }
}
