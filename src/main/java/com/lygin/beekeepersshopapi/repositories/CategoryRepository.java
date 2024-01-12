package com.lygin.beekeepersshopapi.repositories;

import com.lygin.beekeepersshopapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
