package com.project.always;

import com.project.always.utils.elasticsearch.repository.BarSearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = BarSearchRepository.class))
@SpringBootApplication
public class AlwaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlwaysApplication.class, args);
	}

}
