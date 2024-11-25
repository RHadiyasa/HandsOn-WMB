package com.enigma.wmb.entity;

import com.enigma.wmb.constant.Constant;
import com.enigma.wmb.constant.MenuCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = Constant.MENU_TABLE)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "price", nullable = false, columnDefinition = "bigint check (price > 0)")
    private Long price;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private MenuCategory category;
}
