package cl.challenge.tenpo.dtos;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginUserInDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
