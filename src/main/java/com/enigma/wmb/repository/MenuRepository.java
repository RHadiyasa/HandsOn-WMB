package com.enigma.wmb.repository;


import com.enigma.wmb.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// MenuRepository melakukan extends ke JpaRepository bertujuan
// untuk menggunakan method yang disediakan oleh Spring Data JPA
// untuk berkomunikasi langsung dengan database, dan secara
// otomatis MenuRepository akan dibuat sebagai `bean`, yang
// bisa di konsumsi oleh class lain yang membutuhkan.

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {

}
