package com.leonardo.api;

import com.leonardo.convert.UserConvert;
import com.leonardo.entity.Role;
import com.leonardo.entity.User;
import com.leonardo.entity.dto.UserDTO;
import com.leonardo.entity.resource.ERole;
import com.leonardo.entity.resource.LoginRequest;
import com.leonardo.service.IRoleService;
import com.leonardo.service.IUserService;
import com.leonardo.service.impl.CustomUserDetail;
import com.leonardo.util.JwtTokenProvider;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationAPITest {

    private MockMvc mockMvc;

    @Mock private AuthenticationManager authenticationManager;
    @Mock private IUserService userService;
    @Mock private JwtTokenProvider tokenProvider;
    @Mock private UserConvert userConvert;
    @Mock private IRoleService roleService;
    @Mock private JavaMailSender mailSender;

    @InjectMocks
    private AuthenticationAPI authenticationAPI;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationAPI).build();
    }

    @Test
    void authenticateUser_Success() throws Exception {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        CustomUserDetail userDetail = mock(CustomUserDetail.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetail);
        when(tokenProvider.generateToken(userDetail)).thenReturn("mocked-jwt-token");

        // Act & Assert
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("mocked-jwt-token"));
    }

    @Test
    void registerUser_Success() throws Exception {
        // Arrange
        UserDTO userDto = new UserDTO();
        User user = new User();
        user.setEmail("test@example.com");
        user.setFullname("Test User");

        Role role = new Role();
        role.setRoleName(ERole.ROLE_CUSTOMER.name());

        when(userConvert.toDocs(any(UserDTO.class))).thenReturn(user);
        when(roleService.findByRoleName(ERole.ROLE_CUSTOMER.name())).thenReturn(role);
        when(userService.RegisterUser(any(User.class))).thenReturn(true);

        // Mocking Mail Sender components
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act & Assert
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Register Success"));

        verify(userService, times(1)).RegisterUser(any(User.class));
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }

    @Test
    void checkVerifyCode_Success() throws Exception {
        // Arrange
        String code = "valid-code";
        User user = new User();
        user.setEmailVerified(false);

        when(userService.findOneByVerifyCode(code)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/api/verify").param("code", code))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService).updateUser(user);
    }

    @Test
    void checkVerifyCode_AlreadyVerified_ReturnsFalse() throws Exception {
        // Arrange
        String code = "valid-code";
        User user = new User();
        user.setEmailVerified(true); // Already verified

        when(userService.findOneByVerifyCode(code)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/api/verify").param("code", code))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
