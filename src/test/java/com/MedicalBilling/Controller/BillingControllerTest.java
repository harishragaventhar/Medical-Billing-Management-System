package com.MedicalBilling.Controller;

import com.MedicalBilling.Config.SecurityConfig; 
import com.MedicalBilling.Mapper.DenialMapper;
import com.MedicalBilling.Repository.DenialRepository;
import com.MedicalBilling.service.AutomationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import; 
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BillingController.class)
@Import(SecurityConfig.class) 
public class BillingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutomationService automationService;

    @MockBean
    private DenialRepository denialRepository;

    @MockBean
    private DenialMapper denialMapper;

    @Test
    public void testStatusEndpoint_Public() throws Exception {
        
        mockMvc.perform(get("/api/billing/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("System is Online. AR Bot is Ready."));
    }

    @Test
    public void testRunBot_WithoutLogin_ShouldFail() throws Exception {
        mockMvc.perform(post("/api/billing/run-bot/1").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testRunBot_WithLogin_ShouldPass() throws Exception {
        when(automationService.runAutomation(anyLong()))
                .thenReturn("SUCCESS: Logic Applied. New Status: AUTO_ACTION");

        mockMvc.perform(post("/api/billing/run-bot/1").with(csrf()))
                .andExpect(status().isOk());
    }
}