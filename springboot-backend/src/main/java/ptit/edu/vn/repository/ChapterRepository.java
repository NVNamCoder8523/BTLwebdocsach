package ptit.edu.vn.repository;

import org.springframework.stereotype.Repository;

import ptit.edu.vn.entity.Chapter;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

}
