package com.pet.dostavochka;

import com.pet.dostavochka.Model.Product;
import com.pet.dostavochka.Repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class DostavochkaApplicationTests {

	@MockBean
	ProductRepository productRepository;

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testAddProducts() {
		List<Product> products = Arrays.asList(
				new Product("1","1","1","1",1),
				new Product("1","1","1","1",1)
		);

		when(productRepository.findAll()).thenReturn(products);

		Assert.assertEquals(productRepository.findAll(), products);
	}

	@Test
	public void testGetProducts() throws Exception {
		setUp();
		List<Product> products = Arrays.asList(
				new Product("1","1","burger","1",1),
				new Product("1","1","burger","1",1)
		);

		when(productRepository.findAllByCategory("burger")).thenReturn(products);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/list?category=burger"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}



}
