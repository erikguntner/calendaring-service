package com.hfla.demo.services;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nylas.Calendar;
import com.nylas.Event;
import com.nylas.EventQuery;
import com.nylas.NylasAccount;
import com.nylas.NylasClient;
import com.nylas.RemoteCollection;
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
  public RemoteCollection<Event> getEvents() throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendar primaryCalendar = calendarService.getPrimaryCalendar();

    // Instant start = ZonedDateTime.now().toInstant();
    // Instant end = start.plus(10, ChronoUnit.DAYS);
    Instant start = LocalDate.now().atTime(14, 0).toInstant(ZoneOffset.UTC);
    Instant end = start.plus(14, ChronoUnit.DAYS);


    EventQuery query = new EventQuery().calendarId(primaryCalendar.getId()).startsAfter(start).endsBefore(end).limit(50);
    RemoteCollection<Event> events = account.events().list(query);

    return events;
  }
  
}