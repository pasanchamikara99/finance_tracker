package org.fianancetracker.backend.repository;

import org.fianancetracker.backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT C FROM Category as C WHERE C.type = :type ")
    List<Category> findByType(@Param("type") String type);
}
