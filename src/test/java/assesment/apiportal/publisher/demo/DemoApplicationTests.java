package assesment.apiportal.publisher.demo;

import assesment.apiportal.publisher.demo.config.AuthEntryPointJwt;
import assesment.apiportal.publisher.demo.config.JwtUtils;
import assesment.apiportal.publisher.demo.repositories.RoleRepository;
import assesment.apiportal.publisher.demo.repositories.UserRepository;
import assesment.apiportal.publisher.demo.services.ApiPublisherService;
import assesment.apiportal.publisher.demo.services.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest
class DemoApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserDetailsServiceImpl userDetailsService;

	@MockBean
	AuthEntryPointJwt authEntryPointJwt;

	@MockBean
	UserRepository userRepository;

	@MockBean
	RoleRepository roleRepository;

	@MockBean
	JwtUtils jwtUtils;

	@MockBean
	ApiPublisherService apiPublisherService;

	@Test
	public void contextLoads() throws Exception {

//		Mockito.when(userRepository.findAll()).thenReturn(Collections.emptyList());

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/publisher/public")
				.accept(MediaType.APPLICATION_JSON)
		).andReturn();

		System.out.println(mvcResult.getResponse());
//		Mockito.verify(userRepository).findAll();

	}

}
