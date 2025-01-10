package com.sdlc.pro.analytics.analyticstoolapp.intercepptor;


import com.sdlc.pro.analytics.analyticstoolapp.model.RequestTracker;
import com.sdlc.pro.analytics.analyticstoolapp.repository.RequestRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class AnalyticsInterceptor implements HandlerInterceptor {
    @Autowired
    RequestRepository requestRepository;

    private List<String> skipUrl = List.of(
            "/analytics/dashbord"


    );
    private static final SimpleDateFormat dtformat = new SimpleDateFormat("dd-MM-yyy hh:mm:ss a");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long st =System.currentTimeMillis();
        request.setAttribute("start_time",st);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       if (modelAndView != null)
       {
           request.setAttribute("page_name",modelAndView.getView());
       }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       long st = (long) request.getAttribute("start_time");
       long ed = System.currentTimeMillis();

       if (skipUrl.stream().anyMatch(u->u.equals(request.getRequestURI())))
       {
           return;
       }
        String time = dtformat.format(new Date(st));
       var  tracker  =new RequestTracker(
   request.getRemoteAddr(),
               request.getMethod(),
               request.getRequestURI(),
               (String)  request.getAttribute("page_name"),
               time,
               Thread.currentThread().getName(),
               (ed-st)

       );
       requestRepository.addRequestTracker(tracker);



    }
}
