package com.example.msumico.controller;
import com.example.msumico.model.ProductDto;
import com.example.msumico.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@Valid @RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<ProductDto> getAllProducts(@PageableDefault(
            size = 4, direction = Sort.Direction.ASC) final Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductDtoById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id,@Valid @RequestBody ProductDto productDto) {
        productService.updateProductInfo(id, productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}

