package com.example.demo.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    private String image; // Validation sẽ xử lý thủ công ở Controller cho file upload

    @NotNull(message = "Giá không được để trống")
    @Min(value = 1, message = "Giá phải từ 1")
    @Max(value = 9999999, message = "Giá tối đa 9.999.999")
    private Double price;

    private String category;
}