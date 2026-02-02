package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final String UPLOAD_DIR = "src/main/resources/static/images/";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        return "home";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", Arrays.asList("Điện thoại", "Laptop"));
        return "add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult result,
                       @RequestParam("imageFile") MultipartFile imageFile,
                       Model model) throws IOException {

        if (imageFile.isEmpty()) {
            result.rejectValue("image", "error.product", "Vui lòng chọn hình ảnh");
        }

        if (result.hasErrors()) {
            model.addAttribute("categories", Arrays.asList("Điện thoại", "Laptop"));
            return "add";
        }

        // Xử lý lưu file
        String fileName = imageFile.getOriginalFilename();
        Path staticPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(staticPath)) Files.createDirectories(staticPath);
        Files.copy(imageFile.getInputStream(), staticPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        product.setImage(fileName);
        productService.add(product);
        return "redirect:/products";
    }
}