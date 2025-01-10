package com.sdlc.pro.analytics.analyticstoolapp.event;

import com.sdlc.pro.analytics.analyticstoolapp.model.MatchInfo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class CricketMatchUpdateEvent extends ApplicationEvent {
    private final MatchInfo matchInfo;
    public CricketMatchUpdateEvent(Object source, MatchInfo matchInfo) {
        super(source);
        this.matchInfo = matchInfo;
    }
}
