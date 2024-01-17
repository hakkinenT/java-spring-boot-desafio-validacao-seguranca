package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    public EventDTO insert(EventDTO dto){
        Event event = new Event();
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());

        City city = cityRepository.getReferenceById(dto.getCityId());

        event.setCity(city);

        event = eventRepository.save(event);

        return new EventDTO(event);
    }

    public Page<EventDTO> findAll(Pageable pageable){
        Page<Event> events = eventRepository.findAll(pageable);
        return events.map(event -> new EventDTO(event));
    }
}
