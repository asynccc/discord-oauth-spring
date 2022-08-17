package com.freedom.accountauth.util;

import lombok.Builder;

import javax.servlet.http.Cookie;

@Builder
public class CookieBuilder {

    private String name;
    private String value;

    private String path;

    private int maxAge;

    private boolean secure;
    private boolean httpOnly;

    public Cookie toCookie() {
        var cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);

        return cookie;
    }

    /*

    var cookie = new Cookie("LUPI_SESSION", String.valueOf(optUser.get().getId()));
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    cookie.setPath("/");
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);



     */

}
