package org.example.hahaton.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
public class AuthRequest
{
    private String token;

    private Collection<?> roles;
}
