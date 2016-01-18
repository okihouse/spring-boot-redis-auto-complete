package task.autocomplete.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AutoCompleteTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void before(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	
	@Test
	public void autocomplete() throws Exception {
		String word = "autocomplete";
		mockMvc.perform(
				get("/api/{word}", word))				
					.andDo(print())
					.andExpect(status().isOk());
	}
	
	@Test
	public void addWord() throws Exception {
		String word = "autocomplete";
		mockMvc.perform(
				put("/api/add/{word}", word))				
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value("success"));
	}
	
	@Test
	public void increaseScore() throws Exception {
		String word = "autocomplete";
		mockMvc.perform(
				post("/api/{word}", word))				
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value("success"));
	}
}
