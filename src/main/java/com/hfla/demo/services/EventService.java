package com.hfla.demo.services;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nylas.Calendar;
import com.nylas.Event;
import com.nylas.EventQuery;
import com.nylas.NylasAccount;
import com.nylas.NylasClient;
import com.nylas.RequestFailedException;

@Component
public class EventService {

  private final CalendarService calendarService;
  private static NylasClient client = new NylasClient();

  @Value("${access.token}")
  private String accessToken;

  @Autowired
  public EventService(CalendarService calendarService) {
    this.calendarService = calendarService;
  }

  // The the next 50 events from the next 30 days
  public List<Event> getEvents() throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendar primaryCalendar = calendarService.getPrimaryCalendar();

    Instant start = ZonedDateTime.now().toInstant();
    Instant end = start.plus(10, ChronoUnit.DAYS);

    EventQuery query = new EventQuery().calendarId(primaryCalendar.getId()).startsAfter(start).startsBefore(end).limit(50);
    List<Event> events = account.events().list(query).fetchAll();

    return events;
  }
}