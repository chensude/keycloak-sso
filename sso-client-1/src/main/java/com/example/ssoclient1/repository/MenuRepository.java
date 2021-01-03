package com.example.ssoclient1.repository;

import com.example.ssoclient1.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author csd
 * @desc
 * @date 1/3/21 1:25 PM
 **/
@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {

    @Query("SELECT DISTINCT m FROM Menu m JOIN m.roles r WHERE r IN :roleNames")
    List<Menu> findMenuByRoles(@Param("roleNames") List<String> roleNames);
}