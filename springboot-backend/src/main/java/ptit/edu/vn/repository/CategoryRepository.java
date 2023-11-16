package ptit.edu.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ptit.edu.vn.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
