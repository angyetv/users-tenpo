package cl.challenge.tenpo.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginUserInDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
