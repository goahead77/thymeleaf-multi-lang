package cn.wenqi.config;

import cn.wenqi.interceptor.LangInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author wenqi
 */
@Configuration
public class LangConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LangInterceptor langInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(langInterceptor).addPathPatterns("/**");
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("utf-8");//不然中文乱码...
        messageSource.setBasename("home");
        return messageSource;
    }

    @Bean
    public SessionLocaleResolver localeResolver(){
        return new SessionLocaleResolver();
    }
}
