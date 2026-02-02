package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> listProducts = new ArrayList<>();

    public List<Product> getAll() { return listProducts; }

    public void add(Product product) {
        product.setId(listProducts.size() + 1);
        listProducts.add(product);
    }

    public Product getById(int id) {
        return listProducts.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void update(Product product) {
        for (int i = 0; i < listProducts.size(); i++) {
            if (listProducts.get(i).getId() == product.getId()) {
                listProducts.set(i, product);
                break;
            }
        }
    }

    public void delete(int id) {
        listProducts.removeIf(p -> p.getId() == id);
    }
}