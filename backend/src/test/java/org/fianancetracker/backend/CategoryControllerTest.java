package org.fianancetracker.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fianancetracker.backend.dto.CategoryDTO;
import org.fianancetracker.backend.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = { CategoryControllerTest.class })
public class CategoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAddCategorySuccess() throws Exception {
        // Mock CategoryDTO object
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId("1");
        categoryDTO.setDescription("Test Category");
        categoryDTO.setType("Test Type");

        // Mock CategoryService behavior
        Mockito.when(categoryService.addCategory(categoryDTO)).thenReturn("");

        // Create mock MVC environment
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Perform POST request with CategoryDTO object
        MvcResult result = mockMvc.perform(post("/category/addCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(categoryDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        // Verify response message
        String response = result.getResponse().getContentAsString();
        assertEquals("Category Added Successfully", response);
    }

    @Test
    public void testAddCategoryValidationFail() throws Exception {
        // Mock CategoryDTO object with invalid data
        CategoryDTO categoryDTO = new CategoryDTO();

        // Mock CategoryService behavior (not called)
        Mockito.verifyNoInteractions(categoryService);

        // Create mock MVC environment
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Perform POST request with invalid CategoryDTO object
        MvcResult result = mockMvc.perform(post("/category/addCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(categoryDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Verify response message contains validation error
        String response = result.getResponse().getContentAsString();
        assertTrue(response.contains("Cannot be Empty"));
    }


    @Test
    public void testAddCategoryServiceFail() throws Exception {
        // Mock CategoryDTO object
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDescription("Test Category");
        categoryDTO.setType("Test Type");

        // Mock CategoryService behavior to return error message
        Mockito.when(categoryService.addCategory(categoryDTO)).thenReturn("Error adding category");

        // Create mock MVC environment
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Perform POST request with CategoryDTO object
        MvcResult result = mockMvc.perform(post("/category/addCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(categoryDTO)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify response message contains service error
        int response = result.getResponse().getStatus();
        assertEquals(500, response);
    }


    private static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
