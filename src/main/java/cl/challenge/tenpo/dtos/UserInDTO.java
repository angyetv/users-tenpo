package cl.challenge.tenpo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInDTO {

    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
}
