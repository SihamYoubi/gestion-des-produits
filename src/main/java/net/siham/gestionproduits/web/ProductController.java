package net.siham.gestionproduits.web;

import net.siham.gestionproduits.entities.Product;
import net.siham.gestionproduits.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    //injection via le constructeur
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/index")
    public String index(Model model){
       List<Product> products=  productRepository.findAll();
       model.addAttribute("productList", products);
        return "products";
    }
}
