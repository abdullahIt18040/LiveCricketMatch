package com.sdlc.pro.analytics.analyticstoolapp.repository;


import com.sdlc.pro.analytics.analyticstoolapp.model.RequestTracker;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class RequestRepository {
    private final List<RequestTracker> trackers = new CopyOnWriteArrayList<>();
     public void addRequestTracker(RequestTracker tracker)
     {
         trackers.add(tracker);
     }
     public List<RequestTracker> getTrackers()
     {
         return List.copyOf(trackers);
     }
}
