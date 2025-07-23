package sia.tacoCloud.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sia.tacoCloud.dao.OrderRepository;
import sia.tacoCloud.data.Taco;
import sia.tacoCloud.data.TacoOrder;


import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderRepository orderRepo;

    private TacoOrder testOrder;

    @BeforeEach
    public void setup() {
        testOrder = new TacoOrder();
        testOrder.setDeliveryName("Test User");
        testOrder.setDeliveryStreet("123 Main St");
        testOrder.setDeliveryCity("Test City");
        testOrder.setDeliveryState("TS");
        testOrder.setDeliveryZip("12345");
        testOrder.setCcNumber("4111111111111111");
        testOrder.setCcExpiration("12/25");
        testOrder.setCcCVV("123");

        Taco taco = new Taco();
        taco.setName("Test Taco");
        testOrder.addTaco(taco);
    }

    @Test
    public void showOrderForm_shouldReturnOrderFormView() throws Exception {
        mockMvc.perform(get("/orders/current")
                        .sessionAttr("tacoOrder", testOrder))
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"))
                .andExpect(model().attributeExists("tacoOrder"))
                .andExpect(model().attribute("tacoOrder", testOrder));
    }

    @Test
    public void processOrder_withValidData_shouldSaveAndRedirect() throws Exception {
        mockMvc.perform(post("/orders")
                        .sessionAttr("tacoOrder", testOrder)
                        .param("deliveryName", testOrder.getDeliveryName())
                        .param("deliveryStreet", testOrder.getDeliveryStreet())
                        .param("deliveryCity", testOrder.getDeliveryCity())
                        .param("deliveryState", testOrder.getDeliveryState())
                        .param("deliveryZip", testOrder.getDeliveryZip())
                        .param("ccNumber", testOrder.getCcNumber())
                        .param("ccExpiration", testOrder.getCcExpiration())
                        .param("ccCVV", testOrder.getCcCVV()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttributeDoesNotExist("tacoOrder"));

        verify(orderRepo).save(any(TacoOrder.class));
    }


    @Test
    public void processOrder_withInvalidData_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/orders")
                        .sessionAttr("tacoOrder", testOrder)
                        .param("deliveryName", "")  // Пустое имя - невалидное
                        .param("deliveryStreet", testOrder.getDeliveryStreet())
                        .param("deliveryCity", testOrder.getDeliveryCity())
                        .param("deliveryState", testOrder.getDeliveryState())
                        .param("deliveryZip", testOrder.getDeliveryZip())
                        .param("ccNumber", testOrder.getCcNumber())
                        .param("ccExpiration", testOrder.getCcExpiration())
                        .param("ccCVV", testOrder.getCcCVV()))
                .andExpect(status().isBadRequest()); // Ожидаем 400 Bad Request
    }


    @Test
    public void sessionAttributes_shouldContainTacoOrderWithTacos() throws Exception {
        mockMvc.perform(get("/orders/current")
                        .sessionAttr("tacoOrder", testOrder))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("tacoOrder", notNullValue()))
                .andExpect(request().sessionAttribute("tacoOrder",
                        hasProperty("tacos", hasSize(greaterThan(0)))));
    }

    @Test
    public void processOrder_shouldClearSession() throws Exception {
        mockMvc.perform(post("/orders")
                        .sessionAttr("tacoOrder", testOrder)
                        .flashAttr("tacoOrder", testOrder))
                .andExpect(request().sessionAttributeDoesNotExist("tacoOrder"));
    }

    @Test
    public void orderForm_shouldPopulateModelWithSessionTacoOrder() throws Exception {
        mockMvc.perform(get("/orders/current")
                        .sessionAttr("tacoOrder", testOrder))
                .andExpect(model().attribute("tacoOrder", testOrder));
    }
}