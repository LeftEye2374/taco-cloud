package sia.tacoCloud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import sia.tacoCloud.data.taco.Taco;
import sia.tacoCloud.data.taco.TacoOrder;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private DesignTacoController designTacoController;

    @Test
    public void testOrderForm() throws Exception {
        TacoOrder expectedOrder = new TacoOrder();

        mockMvc.perform(get("/orders/current").sessionAttr("tacoOrder", expectedOrder))
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"))
                .andExpect(model().attributeExists("tacoOrder"))
                .andExpect(model().attribute("tacoOrder", expectedOrder));
    }

    @Test
    public void testProcessOrder() throws Exception {
        TacoOrder order = new TacoOrder();
        order.setDeliveryName("Test Name");

        mockMvc.perform(post("/orders")
                        .sessionAttr("tacoOrder", order)
                        .param("deliveryName", "Test Name")
                        .param("deliveryStreet", "123 Main St")
                        .param("deliveryCity", "Anytown")
                        .param("deliveryState", "CA")
                        .param("deliveryZip", "12345")
                        .param("ccNumber", "4111111111111111")
                        .param("ccExpiration", "12/25")
                        .param("ccCVV", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttributeDoesNotExist("tacoOrder")); // Проверяем очистку сессии
   }

    @Test
    public void testSessionAttributes() throws Exception {
        TacoOrder order = new TacoOrder();
        order.addTaco(new Taco());

        mockMvc.perform(get("/orders/current")
                        .sessionAttr("tacoOrder", order))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("tacoOrder", notNullValue()))
                .andExpect(request().sessionAttribute("tacoOrder",
                hasProperty("tacos", hasSize(greaterThan(0)))));
    }
}