package cn.edu.nju.user_story_mapping;

import cn.edu.nju.user_story_mapping.dao.EditLogDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Enumeration;

@Aspect
@Component
public class EditLogAspect {

    @Autowired
    private EditLogDao editLogDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * cn.edu.nju.user_story_mapping.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        Enumeration<String> enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String name = enu.nextElement();
//            logger.info("name:{},value:{}", name, request.getParameter(name));
//        }

        if (request.getMethod().equals("POST")) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            String url = request.getRequestURL().toString();
            String[] urlDivided = url.split("/");
            if (urlDivided.length < 2) {
                return;
            }
            String type = urlDivided[urlDivided.length - 2];
            if (type.equals("release")) {
                Enumeration<String> enu = request.getParameterNames();
                while (enu.hasMoreElements()) {
                    String name = enu.nextElement();

                    logger.info("name:{},value:{}", name, request.getParameter(name));
                }

            }


        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}