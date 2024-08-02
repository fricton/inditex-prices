package com.fricton.inditex.prices.infrastructure.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fricton.inditex.prices.config.PricesApplication;
import com.fricton.inditex.prices.config.SpringAppConfiguration;

@AutoConfigureMockMvc
@SpringBootTest(classes = { PricesApplication.class, SpringAppConfiguration.class })
@ActiveProfiles("test")
public class PricesControllerIT {

	@Autowired
	private MockMvc mockMvc;

	static Stream<PriceRequest> paramsRequestProvider() {
		return Stream.of(

				new PriceRequest(1, 35455, "2020-06-14T10:00:00.000Z", BigDecimal.valueOf(35.50)),
				new PriceRequest(1, 35455, "2020-06-14T16:00:00.000Z", BigDecimal.valueOf(25.45)),
				new PriceRequest(1, 35455, "2020-06-14T21:00:00.000Z", BigDecimal.valueOf(35.50)),
				new PriceRequest(1, 35455, "2020-06-15T10:00:00.000Z", BigDecimal.valueOf(30.50)),
				new PriceRequest(1, 35455, "2020-06-16T21:00:00.000Z", BigDecimal.valueOf(38.95))
				
		);
	}

	@ParameterizedTest
	@MethodSource("paramsRequestProvider")
	void getProductPrice_getOkResponses(PriceRequest priceRequest) throws Exception {
		mockMvc.perform(get("/v1/prices").param("brandId", priceRequest.getBrandId().toString())
				.param("productId", priceRequest.getProductId().toString())
				.param("applicationDate", priceRequest.getApplicationDate()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(priceRequest.getExpectedPrice().doubleValue())));
	}

	static class PriceRequest {
		private Integer brandId;
		private Integer productId;
		private String applicationDate;
		private BigDecimal expectedPrice;

		public PriceRequest(Integer brandId, Integer productId, String applicationDate, BigDecimal expectedPrice) {
			this.brandId = brandId;
			this.productId = productId;
			this.applicationDate = applicationDate;
			this.expectedPrice = expectedPrice;
		}

		public Integer getBrandId() {
			return brandId;
		}

		public Integer getProductId() {
			return productId;
		}

		public String getApplicationDate() {
			return applicationDate;
		}

		public BigDecimal getExpectedPrice() {
			return expectedPrice;
		}
	}

}
