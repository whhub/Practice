package com.mj.scada.log;

import com.mj.scada.bean.domain.SysLog;
import com.mj.scada.common.AuthConst;
import com.mj.scada.pojo.User;
import com.mj.scada.service.SysLogService;
import com.mj.scada.util.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/*
 *Author:ZouHeng
 *Des:系统日志：切面处理类
 *Date:2019-06-01 16:12
 */
@Aspect
@Component
public class SysLogAspect
{
    @Autowired
    private SysLogService sysLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.mj.scada.log.MyLog)")
    public void logPoinCut() {
    }

    //切面，配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint)
    {
        System.out.println("调用接口");

        //保存日志
        SysLog sysLog=new SysLog();

        //用户名
        //从session中获取用户名
        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user=(User)request.getSession().getAttribute(AuthConst.LOGIN_USER);
        String author="";
        if (user!=null)
        {
            author=user.getUsername();
        }
        sysLog.setUsername(author);
        //日期
        sysLog.setCreateDate(Util.getCurrentDateAndTime());

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

//        //请求的参数
//        Object[] args = joinPoint.getArgs();
//        //将参数所在的数组转换成json
//        String params = JSON.toJSONString(args);
//        sysLog.setParams(params);

//        sysLog.setCreateDate(new Date());
//        //获取用户名
//        sysLog.setUsername(ShiroUtils.getUserEntity().getUsername());
//        //获取用户ip地址
//        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//        sysLog.setIp(IPUtils.getIpAddr(request));

        //调用service保存SysLog实体类到数据库
        sysLogService.save(sysLog);
    }
}
