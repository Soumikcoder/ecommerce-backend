package com.example.ecommerce;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	@Hidden
    @GetMapping("/docs")
    public void docs(HttpServletResponse resp) throws IOException  {
        resp.sendRedirect("swagger-ui/index.html");
    }
}
