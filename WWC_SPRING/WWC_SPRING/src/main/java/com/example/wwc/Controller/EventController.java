package com.example.wwc.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import com.example.wwc.Model.Event;
@RestController
// It maps HTTP requests (GET/POST/PUT/DELETE, etc.) to Java methods and typically returns serialized data (JSON or XML) rather than server-side views.
@RequestMapping("/events") 
public class EventController {
    private List<Event> events = new ArrayList<>();
    @GetMapping
    public List<Event> getAllEvents()
    {
        return events;
    }
    @PostMapping
    public String addEvent(@RequestBody Event event)
    {
        events.add(event);
        return "Event added successfully";
    }
}
