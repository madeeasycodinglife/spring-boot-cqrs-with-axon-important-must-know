package com.example.events.handler;

import com.example.data.User;
import com.example.data.UserRepository;
import com.example.events.UserCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserEventHandler {
    private final UserRepository userRepository;

    public UserEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        User user = new User();
        BeanUtils.copyProperties(event, user);
        userRepository.save(user);
    }
}
