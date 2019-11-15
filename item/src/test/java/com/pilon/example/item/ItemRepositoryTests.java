package com.pilon.example.item;

import com.pilon.example.item.domain.Item;
import com.pilon.example.item.repository.ItemRepository;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

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
@ContextConfiguration(initializers = { ItemRepositoryTests.Initializer.class })
public class ItemRepositoryTests {

    @Autowired
    protected ItemRepository itemRepository;

	// Set up a database before running the tests
	@ClassRule
	@SuppressWarnings("rawtypes")
	public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:9.6")
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
		itemRepository.save(new Item(1L, "Amazon Echo (3rd Gen) - Twilight Blue"));
		itemRepository.save(new Item(2L, "Amazon Echo Studio - Black"));
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
        List<Item> items = itemRepository.findAll();
        assertThat(items.size()).isEqualTo(2);
    }

}
