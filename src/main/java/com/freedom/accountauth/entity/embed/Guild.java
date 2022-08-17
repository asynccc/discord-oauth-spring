package com.freedom.accountauth.entity.embed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable
public class Guild {

    private String id;

    private String name;
    private String icon;

    private boolean owner;
}
