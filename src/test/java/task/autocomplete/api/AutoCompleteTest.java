package task.autocomplete.api;

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
	
	//TODO make test case
	//	GET /api/{word} // auto complete
	//	PUT /api/add/{word} // add word
	//	POST /api/{word} // it raises a score of the word.
	
	@Test
	public void testUserDuplicateEmail() throws Exception {
//		mockMvc.perform(
//				get("/")				
//				.param("", "")
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//					.andDo(print())
//					.andExpect(status().isOk())
//					.andExpect(jsonPath("$.message").value("success"));
	}
}
