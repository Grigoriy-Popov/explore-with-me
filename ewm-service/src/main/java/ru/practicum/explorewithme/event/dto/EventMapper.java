package ru.practicum.explorewithme.event.dto;

import ru.practicum.explorewithme.event.Event;
import ru.practicum.explorewithme.event.State;
import ru.practicum.explorewithme.category.CategoryMapper;
import ru.practicum.explorewithme.user.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {
    public static Event fromNewEventDto(NewEventDto newEventDto) {
        return Event.builder()
                .annotation(newEventDto.getAnnotation())
                .description(newEventDto.getDescription())
                .eventDate(newEventDto.getEventDate())
                .location(newEventDto.getLocation())
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.getRequestModeration())
                .state(State.PENDING)
                .title(newEventDto.getTitle())
                .build();
    }

    public static FullEventDto toFullDto(Event event) {
        return FullEventDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate())
                .initiator(UserMapper.toShortDto(event.getInitiator()))
                .paid(event.isPaid())
                .title(event.getTitle())
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .location(event.getLocation())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.isRequestModeration())
                .state(event.getState())
                .views(event.getViews())
                .build();
    }

    public static ShortEventDto toShortDto(Event event) {
        return ShortEventDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate())
                .initiator(UserMapper.toShortDto(event.getInitiator()))
                .paid(event.isPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public static List<ShortEventDto> toShortDtoList(List<Event> events) {
        return events.stream()
                .map(EventMapper::toShortDto)
                .collect(Collectors.toList());
    }

    public static List<FullEventDto> toFullDtoList(List<Event> events) {
        return events.stream()
                .map(EventMapper::toFullDto)
                .collect(Collectors.toList());
    }
}