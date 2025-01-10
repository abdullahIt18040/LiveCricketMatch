package com.sdlc.pro.analytics.analyticstoolapp.event;

import com.sdlc.pro.analytics.analyticstoolapp.model.FootballInfo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class FootballMatchUpdateEvent extends ApplicationEvent {
    private FootballInfo footballInfo;
    public FootballMatchUpdateEvent(Object source,FootballInfo footballInfo) {
        super(source);
        this.footballInfo = footballInfo;
    }
}
