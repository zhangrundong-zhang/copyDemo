package com.copy.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接口请求时间计算拦截器
 *
 * @author zhongyong
 * @date 20190516
 * @since v1.0
 */
@Slf4j
public class CallTimeLogInterceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<StopWatch> STOP_WATCH = new ThreadLocal<>();

    /**
     * 在方法被调用前执行。在该方法中可以做类似校验的功能。
     * 如果返回true，则继续调用下一个拦截器。
     * 如果返回false，则中断执行，也就是说我们想调用的方法不会被执行，但是你可以修改response为你想要的响应
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是转发请求，不做处理
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }
        StopWatch sw = new StopWatch();
        STOP_WATCH.set(sw);
        sw.start();
        log.info("start url:[{}],method:[{}]", request.getRequestURI(), getMethod(handler));
        return true;
    }

    /**
     * 在方法执行后调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        StopWatch sw = STOP_WATCH.get();
        if (sw == null) {
            return;
        }
        sw.stop();
        sw.start();
    }

    /**
     * 在整个请求处理完毕后进行回调，也就是说视图渲染完毕或者调用方已经拿到响应
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
        //如果是转发请求，不做处理
        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        StopWatch sw = STOP_WATCH.get();
        if (sw == null) {
            return;
        }
        sw.stop();

        log.info("end url:[{}],method:[{}],totalTime:[{}ms],methodTime:[{}ms]", request.getRequestURI(), getMethod(handler),
                sw.getTotalTimeMillis(), sw.getTotalTimeMillis() - sw.getLastTaskTimeMillis());

        STOP_WATCH.remove();
    }

    private String getMethod(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String beanType = handlerMethod.getBeanType().getName();
        String methodName = handlerMethod.getMethod().getName();
        return beanType + "." + methodName;
    }
}
