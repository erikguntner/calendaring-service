package com.hfla.demo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfla.demo.services.CalendarService;
import com.hfla.demo.services.EventService;
import com.nylas.Calendar;
import com.nylas.Event;
import com.nylas.FreeBusy;
import com.nylas.RemoteCollection;
import com.nylas.RequestFailedException;
import com.nylas.TimeSlot;


@RestController
@RequestMapping(path = "/calendar")
public class CalendarController {

  private final CalendarService calendarService;
  private final EventService eventService;

  @Autowired
  public CalendarController(CalendarService calendarService, EventService eventService) {
    this.calendarService = calendarService;
    this.eventService = eventService;
  }

  @GetMapping(path= "/calendars")
  public RemoteCollection<Calendar> getCalendars() throws IOException, RequestFailedException {
      return calendarService.getCalendars();
  }
  
  @GetMapping(path = "/freebusy")
  public List<FreeBusy> getFreeBusy() throws IOException, RequestFailedException {
      return calendarService.checkFreeBusy();
  }

  @GetMapping(path = "/availability")
  public List<TimeSlot> getAvailability() throws IOException, RequestFailedException {
      return calendarService.checkAvailability();
  }

  @GetMapping(path = "/events")
  public RemoteCollection<Event> getEvents() throws IOException, RequestFailedException {
      return eventService.getEvents();
  }
}
