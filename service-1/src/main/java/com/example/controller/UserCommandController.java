package com.example.controller;

import com.example.commands.CreateUserCommand;
import com.example.model.UserRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserCommandController {

    private final CommandGateway commandGateway;

    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserRestModel userRestModel) {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .userId(UUID.randomUUID().toString())
                .userName(userRestModel.getUserName())
                .password(userRestModel.getPassword())
                .build();
        commandGateway.sendAndWait(createUserCommand);
        return "add user";
    }
}
