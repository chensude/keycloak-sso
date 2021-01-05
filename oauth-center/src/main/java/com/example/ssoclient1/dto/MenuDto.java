package com.example.ssoclient1.dto;

import com.example.ssoclient1.domain.entity.Menu;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author csd
 * @desc
 * @date 1/3/21 1:29 PM
 **/
@Data
@NoArgsConstructor
public class MenuDto {

    private Integer id;
    private String label;
    private String icon;
    private int sortOrder;
    private String route;

    private List<MenuDto> subMenus;

    public MenuDto(Menu e) {

        id = e.getId();
        label = e.getLabel();
        icon = e.getIcon();
        sortOrder = e.getSortOrder();
        route = e.getRoute();
    }
}