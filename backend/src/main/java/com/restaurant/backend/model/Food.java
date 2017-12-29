package com.restaurant.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;
    private BigDecimal price;
    private String description;
    private String comment;

    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<FoodToCart> foodToCartList;

    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<FoodToOrder> foodToOrderList;

    public Food() {}

    public Food(String name, Category category, BigDecimal price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FoodToCart> getFoodToCartList() {
        return foodToCartList;
    }

    public void setFoodToCartList(List<FoodToCart> foodToCartList) {
        this.foodToCartList = foodToCartList;
    }

    public List<FoodToOrder> getFoodToOrderList() {
        return foodToOrderList;
    }

    public void setFoodToOrderList(List<FoodToOrder> foodToOrderList) {
        this.foodToOrderList = foodToOrderList;
    }
}
