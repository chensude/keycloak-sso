package com.example.ssoclient1.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author csd
 * @desc
 * @date 1/3/21 1:26 PM
 **/
@Entity
@Table(name = "iam_menu")
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity implements Comparable<Menu> {

    private static final long serialVersionUID = -2112068280721667149L;

    @ManyToOne
    @JoinColumn(name = "parent_menu_id")
    private Menu parentMenu;

    @Column(name = "label")
    private String label;

    @Column(name = "icon")
    private String icon;

    @Column(name = "sort_order")
    private int sortOrder;

    @Column(name = "route")
    private String route;

    @Column(name = "role")
    @ElementCollection
    @CollectionTable(name = "role_menus", joinColumns = @JoinColumn(name = "menu_id"))
    private List<String> roles;

    @Override
    public int compareTo(Menu o) {
        return sortOrder - o.getSortOrder();
    }
}

