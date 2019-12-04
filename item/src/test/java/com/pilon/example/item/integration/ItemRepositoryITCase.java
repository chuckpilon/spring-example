package com.pilon.example.item.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.StreamSupport;

import com.pilon.example.item.domain.Item;
import com.pilon.example.item.domain.ItemBuilder;
import com.pilon.example.item.repository.ItemRepository;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = { ItemRepositoryITCase.Initializer.class } )
public class ItemRepositoryITCase {

	@Autowired
	private ItemRepository itemRepository;
	
	// Set up a database before running the tests
	@ClassRule
	@SuppressWarnings("rawtypes")
	public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:12.1")
		.withDatabaseName("postgres")
		.withUsername("postgres")
		.withPassword("postgress");

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues
					.of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
							"spring.datasource.username=" + postgreSQLContainer.getUsername(),
							"spring.datasource.password=" + postgreSQLContainer.getPassword())
					.applyTo(configurableApplicationContext.getEnvironment());
		}
	}

	@Before
	public void insertTestData() {
        Item item1 = ItemBuilder
            .newInstance()
            .id(1)
            .description("Amazon Echo (3rd Gen) - Twilight Blue")
            .build();
		itemRepository.save(item1);

        Item item2 = ItemBuilder
            .newInstance()
            .id(2)
            .description("Amazon Echo Studio - Black")
            .build();
		itemRepository.save(item2);
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void givenItems_whenFindById_thenGetOk() {
		Optional<Item> item = itemRepository.findById(1L);
		assertThat(item.isPresent()).isTrue();
		assertThat(item.get().getId()).isEqualTo(1L);
	}

	@Test
    public void givenItems_whenFindAll_thenGetOk() {
        Iterable<Item> items = itemRepository.findAll();
		assertThat(StreamSupport.stream(items.spliterator(), false).count()).isEqualTo(2);
    }

}
