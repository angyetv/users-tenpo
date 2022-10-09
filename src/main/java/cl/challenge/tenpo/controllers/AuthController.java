package cl.challenge.tenpo.controllers;

import cl.challenge.tenpo.dtos.JwtDTO;
import cl.challenge.tenpo.dtos.LoginUserInDTO;
import cl.challenge.tenpo.dtos.ResponseDTO;
import cl.challenge.tenpo.dtos.UserInDTO;
import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.entities.User;
import cl.challenge.tenpo.enums.RoleEnum;
import cl.challenge.tenpo.exception.BusinessException;
import cl.challenge.tenpo.exception.InputFieldsErrorException;
import cl.challenge.tenpo.jwt.JwtProvider;
import cl.challenge.tenpo.services.RoleService;
import cl.challenge.tenpo.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final RoleService roleService;

    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody UserInDTO userInDTO,
                                                BindingResult bindingResult) throws BusinessException {
        log.info("Starting register...");
        if (bindingResult.hasErrors()) {
            log.error("Input fields error: {}", bindingResult.getAllErrors());
            throw new InputFieldsErrorException();
        }
        User user = new User(userInDTO.getUsername(), userInDTO.getEmail(),
                passwordEncoder.encode(userInDTO.getPassword()));
        Set<Role> roles = new HashSet<>();
        roleService.checkRoles();
        roles.add(roleService.getByRoleName(RoleEnum.USER));
        if (userInDTO.getRoles().contains(RoleEnum.ADMINISTRATOR.name())) {
            roles.add(roleService.getByRoleName(RoleEnum.ADMINISTRATOR));
        }
        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(200, "Successful registration! Sign in"));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginUserInDTO loginUserInDTO,
                                             BindingResult bindingResult) throws InputFieldsErrorException {
        log.info("Starting login...");
        if (bindingResult.hasErrors()) {
            log.error("Input fields error: {}", bindingResult.getAllErrors());
            throw new InputFieldsErrorException();
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUserInDTO.getUsername(), loginUserInDTO.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            JwtDTO jwtDTO = new JwtDTO(jwt);
            jwtDTO.setCode(200);
            jwtDTO.setMessage("Successful login!");
            return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Authentication error: {}", e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(400, "Authentication error."), HttpStatus.BAD_REQUEST);
        }
    }
}
