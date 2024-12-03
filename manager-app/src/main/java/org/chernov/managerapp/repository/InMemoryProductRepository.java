package org.chernov.managerapp.repository;


import org.chernov.managerapp.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public InMemoryProductRepository(){
        IntStream.range(1,4).forEach(i -> this.products.add(new Product(i, String.format("Товар №%d", i),
                String.format("Описание товара №%d", i))));
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(this.products);
    }

    public Product save(Product product) {
        product.setId(products.stream()
                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId)
                .orElse(0) + 1);
        products.add(product);
        return product;
    }

    public Optional<Product> findProductById(Integer id) {
        return products.stream().filter(product -> Objects.equals(id, product.getId())).findFirst();
    }

    @Override
    public void deleteProductById(Integer id) {
        products.removeIf(product -> Objects.equals(id, product.getId()));
    }
}
