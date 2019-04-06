package cn.edu.nju.user_story_mapping;

import cn.edu.nju.user_story_mapping.dao.ActivityDao;
import cn.edu.nju.user_story_mapping.dao.EditLogDao;
import cn.edu.nju.user_story_mapping.dao.ReleaseDao;
import cn.edu.nju.user_story_mapping.dao.TaskDao;
import cn.edu.nju.user_story_mapping.entity.EditLogEntity;
import cn.edu.nju.user_story_mapping.vo.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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

    @Autowired
    private ReleaseDao releaseDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private TaskDao taskDao;

    private int uid = -1;

    private int mid = -1;

    private String type = "";

    private int itemId = -1;

    private String desc = "";

    private Timestamp updateAt = null;

    private boolean needLog = true;

    @Pointcut("execution(public * cn.edu.nju.user_story_mapping.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        this.uid = -1;
        this.mid = -1;
        this.type = "";
        this.itemId = -1;
        this.desc = "";
        this.updateAt = null;
        this.needLog = true;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (!request.getMethod().equals("POST")) {
            this.needLog = false;
            return;
        }


        String url = request.getRequestURL().toString();
        String[] urlDivided = url.split("/");
        if (urlDivided.length < 2) {
            this.needLog = false;
            return;
        }
        this.type = urlDivided[urlDivided.length - 2];
        if ((!this.type.equals("release")) && (!this.type.equals("task")) && (!this.type.equals("activity")) && (!this.type.equals("story"))) {
            this.needLog = false;
            return;
        }


        this.needLog = true;
        this.desc = urlDivided[urlDivided.length - 1];


        UserVO userVO = (UserVO) request.getSession(false).getAttribute("User");
        this.uid = userVO.getId();


        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            if (name.equals("mid")) {
                this.mid = Integer.parseInt(request.getParameter(name));
            }
//            if (name.equals("rid")) {
//                this.mid = releaseDao.findOne(Integer.parseInt(request.getParameter(name))).getMid();
//                if (this.type.equals("release")) {
//                    this.itemId = Integer.parseInt(request.getParameter(name));
//                }
//            }
            if (name.equals("aid")) {
                this.mid = activityDao.findOne(Integer.parseInt(request.getParameter(name))).getMid();
                if (this.type.equals("activity")) {
                    this.itemId = Integer.parseInt(request.getParameter(name));
                }
            }
            if (name.equals("tid")) {
                int aid = taskDao.findOne(Integer.parseInt(request.getParameter(name))).getAid();
                this.mid = activityDao.findOne(aid).getMid();
                if (this.type.equals("task")) {
                    this.itemId = Integer.parseInt(request.getParameter(name));
                }
            }
            if (name.equals("story") && this.type.equals("story")) {
                this.itemId = Integer.parseInt(request.getParameter(name));
            }
        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        if (!this.needLog) {
            return;
        }

        if (this.itemId == -1) {
            if (ret instanceof StoryVO) {
                this.itemId = ((StoryVO) ret).getSid();
            }
            if (ret instanceof ActivityVO) {
                this.itemId = ((ActivityVO) ret).getAid();
            }
            if (ret instanceof TaskVO) {
                this.itemId = ((TaskVO) ret).getTid();
            }
            if (ret instanceof ReleaseVO) {
                this.itemId = ((ReleaseVO) ret).getRid();
            }
        }


        this.updateAt = new Timestamp(System.currentTimeMillis());


        EditLogEntity editLog = new EditLogEntity();
        editLog.setUid(this.uid);
        editLog.setMid(this.mid);
        editLog.setItemId(this.itemId);
        editLog.setType(this.type);
        editLog.setEditAt(this.updateAt);
        editLog.setDesc(this.desc);
        editLogDao.save(editLog);
    }
}