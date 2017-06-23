package cn.wenqi.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.UUID;

/**
 * @author wenqi
 */
@Component
public class LangInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionLocaleResolver localeResolver;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String lang = httpServletRequest.getParameter("lang");
        if(StringUtils.isEmpty(lang))
            lang= UUID.randomUUID().toString();//随机生成一个UUID，默认调用default
        switch (lang) {
            case "zh_CN":
                Locale l = Locale.CHINA;
                localeResolver.setLocale(httpServletRequest, httpServletResponse, l);
                break;
            case "en":
                 l = Locale.ENGLISH;
                localeResolver.setLocale(httpServletRequest, httpServletResponse, l);
                break;
            default:
                l = Locale.CHINA;
                localeResolver.setLocale(httpServletRequest, httpServletResponse, l);
                break;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , Object o, Exception e) throws Exception {

    }
}
