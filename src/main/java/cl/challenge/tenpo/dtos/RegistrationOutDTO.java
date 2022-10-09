package cl.challenge.tenpo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationOutDTO {
    private Long id;
    private String remoteUser;
    private String method;
    private String endpoint;
    private int statusCode;
}
