package com.github.java4wro.event;

import com.github.java4wro.dto.EventDTO;
import com.github.java4wro.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by RENT on 2017-11-13.
 */
@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    private EventDTO add(@RequestBody EventDTO eventDTO ){
        return eventService.addEvent(eventDTO);
    }

    @GetMapping("/{uuid}")
    public EventDTO findAllEvent(@PathVariable("uuid") String eventUuid) {
        return eventService.getEventByUuid(eventUuid);
    }
}
