package com.drool.demo.object;

public class Order {
    private String name;
    private Integer discount;
    private Integer price;
    private boolean blocked = false;

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Integer getPrice() {
        return price;
    }
}