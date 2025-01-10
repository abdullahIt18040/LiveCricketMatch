package com.sdlc.pro.analytics.analyticstoolapp.controller;

import com.sdlc.pro.analytics.analyticstoolapp.event.CricketMatchUpdateEvent;
import com.sdlc.pro.analytics.analyticstoolapp.event.FootballMatchUpdateEvent;
import com.sdlc.pro.analytics.analyticstoolapp.handler.MatchEventHandler;
import com.sdlc.pro.analytics.analyticstoolapp.model.FootballInfo;
import com.sdlc.pro.analytics.analyticstoolapp.model.MatchInfo;
import com.sdlc.pro.analytics.analyticstoolapp.model.RequestTracker;
import com.sdlc.pro.analytics.analyticstoolapp.repository.RequestRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/analytics")
public class DashbordController {

    @Autowired
    private ApplicationEventPublisher publisher;

@Autowired
private MatchEventHandler matchEventHandler;
    @Autowired
    RequestRepository requestRepository;
@GetMapping("/dashbord")
public String dashbord()
{
    return "wellcome";
}

    @GetMapping("/cricket-match")
    public String cricketMatch()
    {
        return "cricket_match";
    }

    @GetMapping("/get-matchUpdate")
    public SseEmitter progressBar(HttpServletResponse response) throws IOException {
   return matchEventHandler.subscribe();
    }
    @ResponseStatus(HttpStatus.OK)
@PostMapping("/update-match-data")
public void upDateMatchData(@RequestBody MatchInfo matchInfo)
{
    var event = new CricketMatchUpdateEvent(this,matchInfo);
    publisher.publishEvent(event);

}
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update-footballmatch-data")
    public void upDateFootballMatchData(@RequestBody FootballInfo matchInfo)
    {
var event = new FootballMatchUpdateEvent(this,matchInfo);
publisher.publishEvent(event);


    }
    private void sleep(long ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
