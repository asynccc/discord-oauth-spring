package com.freedom.accountauth.service;

import async.oauth2.DiscordAuthentication;
import async.oauth2.Settings;
import com.freedom.accountauth.dto.request.CreateUserRequest;
import com.freedom.accountauth.dto.response.guild.ReadGuildResponse;
import com.freedom.accountauth.dto.response.user.ReadUserResponse;
import com.freedom.accountauth.entity.User;
import com.freedom.accountauth.exception.GuildNotFoundException;
import com.freedom.accountauth.exception.UserAlreadyExistsException;
import com.freedom.accountauth.exception.UserNotFoundException;
import com.freedom.accountauth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    private final DiscordAuthentication authentication;

    public ReadUserResponse create(@NonNull CreateUserRequest request) throws UserAlreadyExistsException {
        if (repository.existsById(request.getId())) {
            throw new UserAlreadyExistsException();
        }

        var user = repository.save(User.builder()
                        .id(request.getId())
                        .username(request.getUsername())
                        .discriminator(request.getDiscriminator())
                        .email(request.getEmail())
                        .token(request.getToken())
                .build());

        return new ReadUserResponse(user);
    }

    @SneakyThrows
    public ReadUserResponse createRaw(DiscordAuthentication authentication, String accessToken) {
        var futureUser = authentication.getUser(accessToken)
                .get()
                .toUser();

        var futureGuilds = new CopyOnWriteArrayList<>(authentication.getGuilds(accessToken)
                .get());

        var optUser = repository.findById(futureUser.getId());

        if (optUser.isPresent()) {
            var user = optUser.get();

            for (var guild : futureGuilds) {
                if(authentication.getUser(guild.getId(), Settings.BOT_USER_ID).get() == null) {
                    futureGuilds.removeIf(future -> future.getId().equals(String.valueOf(guild.getId())));
                }
            }

            user.updateGuilds(futureGuilds);

            repository.save(user);

            return new ReadUserResponse(user);
        } else {
            futureUser.updateGuilds(futureGuilds);
            return new ReadUserResponse(repository.save(futureUser));
        }
    }

    public List<ReadUserResponse> findAll() {
        return repository.findAll().stream()
                .map(ReadUserResponse::new)
                .toList();
    }

    @SneakyThrows
    public ReadUserResponse findById(long id) {
        var user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return new ReadUserResponse(user);
    }

    public List<ReadGuildResponse> findGuilds(long id) throws UserNotFoundException {
        return repository.findById(id)
                .orElseThrow(UserNotFoundException::new)
                .getGuilds().stream()
                .map(guild -> new ReadGuildResponse(guild, authentication))
                .toList();
    }

    public ReadGuildResponse findGuild(long userId, long guildId) throws GuildNotFoundException, UserNotFoundException {
        return findGuilds(userId).stream()
                .filter(guild -> guild.getId().equals(String.valueOf(guildId)))
                .findFirst()
                .orElseThrow(GuildNotFoundException::new);
    }

}
