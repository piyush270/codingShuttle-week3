package com.springboot.jpaTutes;

import com.springboot.jpaTutes.entities.ProductEntity;
import com.springboot.jpaTutes.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class JpaTutesApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){
		ProductEntity productEntity = ProductEntity.builder()
				.sku("Nesle12345")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(12.32))
				.quantity(22)
				.build();

		ProductEntity savedEntity = productRepository.save(productEntity);
		System.out.println(savedEntity);

	}

	@Test
	void getRepository(){
		List<ProductEntity> entity1 = productRepository.findBytitle("Pepsi");
		System.out.println(entity1);

		ProductEntity entity2 = productRepository.findByTitleAndPrice("Nestle Chocolate", BigDecimal.valueOf(12.32));
		System.out.println(entity2);
	}

}
