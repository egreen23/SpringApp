package it.unisalento.se.saw.restapi;

import static org.hamcrest.Matchers.allOf;

import static org.hamcrest.Matchers.hasItem;

import static org.hamcrest.Matchers.hasProperty;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.charset.Charset;
import java.util.Arrays;

import javax.swing.text.AbstractDocument.Content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.jayway.jsonpath.JsonPath;

import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
															MediaType.APPLICATION_JSON.getSubtype(),
															Charset.forName("utf8")); /*costante che instanzia oggetto mediatype,dichiaro ceh voglio un Json con codifica utf8*/
	
	private MockMvc mockMvc;
	
	@Mock
	private IUserService userServiceMock;
	
	
	/* questo in ogni classe di test*/
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userServiceMock)).setViewResolvers(viewResolver()).build();
		/*set up fisso test*/
		
	}
	
	@Test /*scelgo la funzione da testare*/
	public void findUserByIdTest() throws Exception {
	
		User user = new User();
		user.setName("sandro");
		user.setSurname("fiore");
		
		when(userServiceMock.getById(1)).thenReturn(user); /*impostare la condizione di test*/
		
		mockMvc.perform(get("/user/getById/{id}",1))
			.andExpect(status().isOk())  /*mi aspetto una risposta http "OK"*/
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.name", is("sandro")))
			.andExpect(jsonPath("$.surname", is("fiore")));
			
		
		verify(userServiceMock, times(1)).getById(1); /*verifica che con una chiamata al metodo le asserzioni siano corrette*/
		verifyNoMoreInteractions(userServiceMock);	
	}
}
