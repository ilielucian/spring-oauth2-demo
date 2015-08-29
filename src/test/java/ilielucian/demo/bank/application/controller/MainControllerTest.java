package ilielucian.demo.bank.application.controller;

import ilielucian.demo.bank.config.TestConfig;
import ilielucian.demo.bank.config.security.SpringSecurityConfig;
import ilielucian.demo.bank.config.web.WebAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Lucian Ilie.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebAppConfig.class, SpringSecurityConfig.class})
@WebAppConfiguration
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void adminPage_NotAuthenticated() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login")) //why doesn't it work with relative path?
                .andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(username = "admin", password = "123456", roles = "ADMIN")
    // TODO
    // use mock user instead of real user after implementing user registration in DB
    public void adminPage_Authenticated() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(authenticated());
    }

    @Test
    @WithMockUser(username = "lucian", password = "123456", roles = "USER")
    // TODO
    // use mock user instead of real user after implementing user registration in DB
    public void accessDenied_UserNotAuthorized() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden())
                .andExpect(authenticated());
    }
}
