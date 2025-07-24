package sia.tacoCloud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import sia.tacoCloud.data.taco.Taco;
import sia.tacoCloud.data.taco.TacoOrder;

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
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
    public void testProcessTaco() throws Exception {
        mockMvc.perform(post("/design")
                        .param("name", "Test Taco")
                        .param("ingredients", "FLTO,GRBF,CHED"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders/current"));
    }

    @Test
    public void testModelAttributes() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(model().attributeExists("tacoOrder")) // Проверяем наличие атрибута
                .andExpect(model().attribute("tacoOrder", instanceOf(TacoOrder.class))) // Проверяем тип
                .andExpect(model().attributeExists("taco")) // Проверяем наличие атрибута
                .andExpect(model().attribute("taco", instanceOf(Taco.class))); // Проверяем тип
    }
}