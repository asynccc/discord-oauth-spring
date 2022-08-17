package com.freedom.accountauth.controller;

import async.oauth2.DiscordAuthentication;
import async.oauth2.model.ChannelType;
import com.freedom.accountauth.exception.GuildNotFoundException;
import com.freedom.accountauth.exception.UserNotFoundException;
import com.freedom.accountauth.service.UserService;
import com.freedom.accountauth.util.CookieBuilder;
import com.google.gson.GsonBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class ProfileController {

    @NonNull
    private UserService service;

    @NonNull
    private RestOperations restOperations;

    @NonNull private DiscordAuthentication authentication;

    @GetMapping("/profile/@me")
    public ResponseEntity<?> profile(@CookieValue("LUPI_SESSION") String cookie) {
        if (cookie.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var user = service.findById(Long.parseLong(cookie));

        return ResponseEntity.ok(user);
    }

    @GetMapping("/profile/@me/guilds/{id}")
    public ResponseEntity<?> guild(@CookieValue("LUPI_SESSION") String cookie, @PathVariable long id) throws UserNotFoundException, GuildNotFoundException {
        if (cookie.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var guild = service.findGuild(Long.parseLong(cookie), id);

        return ResponseEntity.ok(guild);
    }


    @GetMapping("/profile/@me/guilds")
    public ResponseEntity<?> findGuilds(@CookieValue("LUPI_SESSION") String cookie) throws UserNotFoundException {
        if (cookie.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var guilds = service.findGuilds(Long.parseLong(cookie));

        return ResponseEntity.ok(guilds);
    }

    @SneakyThrows
    @GetMapping("/oauth/discord/callback")
    public ResponseEntity<?> code(HttpServletResponse servletResponse, @RequestParam String code) {
        authentication.setCode(code);

        final var token = authentication.getToken(restOperations);
        final var user = service.createRaw(authentication, token.getAccessToken());

        var cookieBuilder = CookieBuilder.builder()
                .name("LUPI_SESSION")
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .httpOnly(true)
                .value(String.valueOf(user.getId())).build();

        servletResponse.addCookie(cookieBuilder.toCookie());
        servletResponse.sendRedirect("http://localhost:3000/servers");

        return ResponseEntity.badRequest().build();
    }


}
