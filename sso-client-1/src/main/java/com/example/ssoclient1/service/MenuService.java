package com.example.ssoclient1.service;

import com.example.ssoclient1.domain.entity.Menu;
import com.example.ssoclient1.dto.MenuDto;
import com.example.ssoclient1.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author csd
 * @desc
 * @date 1/3/21 1:29 PM
 **/
@Service
public class MenuService extends BaseService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuDto> findMenu() {

        CurrentUser cu = getCurrentUser();

        List<Menu> unorderedMenus = menuRepository.findMenuByRoles(cu.getRoles());
        List<MenuDto> parentMenuDtos = unorderedMenus.stream().filter(e -> e.getParentMenu() == null).map(MenuDto::new).collect(Collectors.toList());

        parentMenuDtos = parentMenuDtos.stream().map(e -> {
            e.setSubMenus(unorderedMenus.stream().filter(sm -> sm.getParentMenu() != null && sm.getParentMenu().getId().equals(e.getId())).sorted().map(MenuDto::new)
                    .collect(Collectors.toList()));
            return e;
        }).collect(Collectors.toList());

        return parentMenuDtos;
    }
}

