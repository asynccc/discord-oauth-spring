package com.freedom.accountauth.dto.response.guild;

import async.oauth2.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReadRoleResponse {

    private String id;
    private String name;
    private String color;

    public ReadRoleResponse(Role role) {
        this.id = String.valueOf(role.getId());
        this.name = role.getName();

        this.color = Integer.toHexString(role.getColor());
    }
}
