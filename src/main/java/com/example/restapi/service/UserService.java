package com.example.restapi.service;

import com.example.restapi.dto.exception.UserNotExceptionResponse;
import com.example.restapi.dto.response.ResponseRetrieveDto;
import com.example.restapi.entity.User.User;
import com.example.restapi.entity.User.UserRepository;
import com.example.restapi.dto.request.RequestCreateDto;
import com.example.restapi.dto.response.ResponseCreateDto;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseCreateDto save(RequestCreateDto requestCreateDto) {

        User user = userRepository.save(requestCreateDto.toEntiy());
        return new ResponseCreateDto(user.getId(), user.getNickname());
    }

    @Transactional
    public ResponseRetrieveDto retrieve(Long id){

        User user = userRepository.findById(id).orElseThrow(()->
                new UserNotExceptionResponse("Not Found User"));

        /*
            WebMvcLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
            URI createdUri = selfLinkBuilder.toUri();
            EventResource eventResource = new EventResource(event);
            eventResource.add(linkTo(EventController.class).withRel("query-events"));
            eventResource.add(selfLinkBuilder.withRel("update-event"));
            eventResource.add(new Link("/docs/index.html#resources-events-create").withRel("profile"));
            return ResponseEntity.created(createdUri).body(eventResource);
         */

        // HateOAS
        ResponseRetrieveDto responseRetrieveDto = new ResponseRetrieveDto(user.getEmail(),user.getNickname());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieve(id));
        /*
            #1
            "self": {
                "href": "http://localhost:8082"
            }
            #2
            "retrieve-user": {
                "href": "http://localhost:8082"
            }
         */
        responseRetrieveDto.add(Link.of(String.valueOf(linkTo))); // #1
        responseRetrieveDto.add(linkTo.withRel("retrieve-user")); // #2

        return responseRetrieveDto;
    }
}
