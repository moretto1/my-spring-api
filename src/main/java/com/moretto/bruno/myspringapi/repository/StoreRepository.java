package com.moretto.bruno.myspringapi.repository;

import com.moretto.bruno.myspringapi.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
