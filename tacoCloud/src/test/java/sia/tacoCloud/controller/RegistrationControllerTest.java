package sia.tacoCloud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sia.tacoCloud.dao.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

//    @Test
//    public void testShowRegistrationForm() throws Exception {
//        mockMvc.perform(get("/register"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("registration"));
//    }
//
//    @Test
//    public void testProcessRegistration() throws Exception {
//        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
//
//        mockMvc.perform(post("/register")
//                        .with(csrf()) // Добавляем CSRF токен
//                        .param("username", "testuser")
//                        .param("password", "testpass")
//                        .param("fullname", "Test User")
//                        .param("street", "123 Test St")
//                        .param("city", "Test City")
//                        .param("state", "TS")
//                        .param("zip", "12345")
//                        .param("phone", "1234567890"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
//
//    @Test
//    public void testProcessRegistrationWithInvalidData() throws Exception {
//        mockMvc.perform(post("/register")
//                        .with(csrf()) // Добавляем CSRF токен
//                        .param("password", "testpass")
//                        .param("fullname", "Test User"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("registration"));
//    }

    @Test
    public void testProcessRegistrationWithoutCsrf() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("password", "testpass"))
                .andExpect(status().isForbidden()); // Ожидаем 403 без CSRF
    }
}