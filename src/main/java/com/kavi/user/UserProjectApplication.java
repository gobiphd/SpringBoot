package com.kavi.user;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UserProjectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UserProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UserProjectApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
