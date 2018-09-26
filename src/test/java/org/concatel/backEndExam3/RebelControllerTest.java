package org.concatel.backEndExam3;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.concatel.backEndExam3.controllers.RebelController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyWebInitializer.class, MySpringContextConfig.class, MyDispatcherServletConfig.class})
@WebAppConfiguration
public class RebelControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RebelController rebelController;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(rebelController)
                .build();
    }

    @Test
    public void testValidPost() throws Exception {
        System.out.println("\nTesting a valid request.\n");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addRebel")
                .param("name", "Yoda")
                .param("planet", "Earth"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        System.out.println("\nTest finished.\n");

        System.out.println("\nTesting a valid request.\n");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addRebel")
                .param("name", "    Yoda     ")
                .param("planet", "    Earth    ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        System.out.println("\nTest finished.\n");
    }

    @Test
    public void testInvalidPostEmptyName() throws Exception {
        System.out.println("\nTesting a request with empty name.\n");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addRebel")
                .param("name", "")
                .param("planet", "Earth")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        System.out.println("\nTest finished.\n");

        System.out.println("\nTesting a request with whitespace name.\n");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addRebel")
                .param("name", "    ")
                .param("planet", "Earth")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        System.out.println("\nTest finished.\n");
    }

    @Test
    public void testInvalidPostEmptyPlanet() throws Exception {
        System.out.println("\nTesting a request with whitespace planet.\n");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addRebel")
                .param("name", "Yoda")
                .param("planet", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        System.out.println("\nTest finished.\n");
        System.out.println("\nTesting a request with whitespace planet.\n");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addRebel")
                .param("name", "Yoda")
                .param("planet", "  ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        System.out.println("\nTest finished.\n");
    }

}
