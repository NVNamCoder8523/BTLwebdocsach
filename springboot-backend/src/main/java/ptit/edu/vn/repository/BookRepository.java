package ptit.edu.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ptit.edu.vn.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    
    public boolean existsByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', ?1, '%')")
    public List<Book> findByTitle(String title);
}
