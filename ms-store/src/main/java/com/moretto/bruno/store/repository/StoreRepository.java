package com.moretto.bruno.store.repository;

import com.moretto.bruno.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
