package com.javaabuser.onlinestore.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return id == category.id && title.equals(category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
