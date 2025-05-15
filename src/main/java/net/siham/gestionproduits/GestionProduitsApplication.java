package net.siham.gestionproduits;

import net.siham.gestionproduits.entities.Product;
import net.siham.gestionproduits.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class GestionProduitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionProduitsApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("Computer").price(7500).quantity(12).build());
            productRepository.save(Product.builder().name("Smart phone").price(4500).quantity(5).build());
            productRepository.save(Product.builder().name("Television").price(4700).quantity(34).build());
            productRepository.findAll().forEach(p->{
                System.out.println(p.toString());
            });
        };

    }

}
