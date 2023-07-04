package com.example.aggregate;

import com.example.commands.CreateUserCommand;
import com.example.events.UserCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class UserAggregate {
    @AggregateIdentifier
    private String userId;
    private String userName;
    private String password;

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand) {
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .userId(createUserCommand.getUserId())
                .userName(createUserCommand.getUserName())
                .password(createUserCommand.getPassword())
                .build();
        AggregateLifecycle.apply(userCreatedEvent);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent) {
        this.userId = userCreatedEvent.getUserId();
        this.userName = userCreatedEvent.getUserName();
        this.password = userCreatedEvent.getPassword();
    }
}
