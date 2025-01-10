package com.sdlc.pro.analytics.analyticstoolapp.handler;

import com.sdlc.pro.analytics.analyticstoolapp.event.CricketMatchUpdateEvent;
import com.sdlc.pro.analytics.analyticstoolapp.event.FootballMatchUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
@Component
public class MatchEventHandler {
    private final Set<SseEmitter> sseEmitters = new CopyOnWriteArraySet<>();

    public SseEmitter subscribe(){
        SseEmitter emitter = new SseEmitter((long) Integer.MAX_VALUE);
        emitter.onError(err -> sseEmitters.remove(emitter));
        emitter.onTimeout(()->sseEmitters.remove(emitter));
        emitter.onCompletion(()-> sseEmitters.remove(emitter));
        sseEmitters.add(emitter);
        return emitter;
    }
  @Async
    @EventListener(CricketMatchUpdateEvent.class)
    public  void cricketMatchUpdate(CricketMatchUpdateEvent event){

      for ( SseEmitter emitter :sseEmitters)
      {
          try {
              var eventbuilder = SseEmitter.event()

                      .name("cricket")
                      .data(event.getMatchInfo()) ;
              emitter.send(

                      eventbuilder
              );

          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }
    }


    @Async
    @EventListener(FootballMatchUpdateEvent.class)
    public  void footballMatchUpdate(FootballMatchUpdateEvent event){

sendData("football",event.getFootballInfo());
    }


    private  void  sendData(String eventName,Object data)
    {
        for ( SseEmitter emitter :sseEmitters)
        {
            try {
                var eventbuilder = SseEmitter.event()

                        .name(eventName)
                        .data(data) ;
                emitter.send(

                        eventbuilder
                );

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
