package cl.challenge.tenpo.controllers;

import cl.challenge.tenpo.dtos.LoginUserInDTO;
import cl.challenge.tenpo.dtos.ResponseDTO;
import cl.challenge.tenpo.dtos.UserInDTO;
import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.enums.RoleEnum;
import cl.challenge.tenpo.exception.BusinessException;
import cl.challenge.tenpo.exception.InputFieldsErrorException;
import cl.challenge.tenpo.services.RoleService;
import cl.challenge.tenpo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.validation.BindingResult;

import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleService roleService;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() throws BusinessException {
        UserInDTO userInDTO = new UserInDTO("user", "test@email.com", "pas",
                Set.of(RoleEnum.USER.name(), RoleEnum.ADMINISTRATOR.name()));
        when(bindingResult.hasErrors()).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("password");
        when(roleService.getByRoleName(any())).thenReturn(new Role(RoleEnum.USER));
        ResponseEntity<ResponseDTO> response = authController.register(userInDTO, bindingResult);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void registerWithInvalidEmail() {
        UserInDTO userInDTO = new UserInDTO("user", "email", "pas",
                Set.of(RoleEnum.USER.name(), RoleEnum.ADMINISTRATOR.name()));
        when(bindingResult.hasErrors()).thenReturn(true);
        assertThrows(InputFieldsErrorException.class, () ->
                authController.register(userInDTO, bindingResult), "Please check the fields and try again.");
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN", "USER"})
    void login() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);
//        HttpSecurity http = HttpSecurity.MvcMatchersRequestMatcherConfigurer;
//        when(authenticationManagerBuilder.getObject()).thenReturn(http.getSharedObject(AuthenticationManager.class));
        LoginUserInDTO loginUserInDTO = new LoginUserInDTO();
        loginUserInDTO.setPassword("ss");
        loginUserInDTO.setUsername("user");
        ResponseEntity<ResponseDTO> response = authController.login(loginUserInDTO, bindingResult);
        assertEquals(400, response.getStatusCode().value());
        assertEquals("Authentication error.", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(400, Objects.requireNonNull(response.getBody()).getCode());
    }

    @Test
    void loginBindingError() {
        when(bindingResult.hasErrors()).thenReturn(true);
        assertThrows(InputFieldsErrorException.class, () ->
                authController.login(new LoginUserInDTO(), bindingResult), "Please check the fields and try again.");
    }
}