package sia.tacoCloud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sia.tacoCloud.dao.UserRepository;
import sia.tacoCloud.data.taco.Taco;
import sia.tacoCloud.data.taco.TacoOrder;

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testShowDesignForm() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attributeExists("wrap"))
                .andExpect(model().attributeExists("protein"))
                .andExpect(model().attributeExists("cheese"))
                .andExpect(model().attributeExists("veggies"))
                .andExpect(model().attributeExists("sauce"))
                .andExpect(model().attributeExists("taco"))
                .andExpect(model().attributeExists("tacoOrder"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testProcessTaco() throws Exception {
        mockMvc.perform(post("/design")
                        .with(csrf()) // Явно добавляем CSRF-токен
                        .param("name", "Test Taco")
                        .param("ingredients", "FLTO,GRBF,CHED"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders/current"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testModelAttributes() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(model().attributeExists("tacoOrder"))
                .andExpect(model().attribute("tacoOrder", instanceOf(TacoOrder.class)))
                .andExpect(model().attributeExists("taco"))
                .andExpect(model().attribute("taco", instanceOf(Taco.class)));
    }
}