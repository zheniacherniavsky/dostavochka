package com.pet.dostavochka;

import com.pet.dostavochka.Configurations.security.jwt.JwtTokenProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class IntegrationTests {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private MockMvc mockMvc;

    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testUnauthorizedJWTAccess() throws Exception {
        setUp();
        String token = "";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/signin"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/orders").header("Authorization", "Bearer " + token))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testAuthorizedJWTAccess() throws Exception {
        setUp();
        String token = "VALID TOKEN WITH ADMIN ACCESS";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/orders").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/acceptOrder?orderId=1").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
