package net.siham.gestionproduits.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import net.siham.gestionproduits.entities.Product;
import net.siham.gestionproduits.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    //injection via le constructeur
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/index")
    public String index(Model model){
       List<Product> products=  productRepository.findAll();
       model.addAttribute("productList", products);
        return "products";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) return "new-product";
        productRepository.save(product);
        return "redirect:/user/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/delete")
    public String delete(@RequestParam(name="id") Long id){
       productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/notAuthorized")
    public String notAutorized(){
        return "not-authorized";
    }
}
