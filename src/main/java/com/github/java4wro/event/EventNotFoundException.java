package com.github.java4wro.event;

/**
 * Created by RENT on 2017-11-20.
 */
public class EventNotFoundException extends RuntimeException {
    private String eventUuid;

    public String getEventUuid() {
        return eventUuid;
    }

    public EventNotFoundException(String eventUuid) {
        this.eventUuid = eventUuid;


    }
}
