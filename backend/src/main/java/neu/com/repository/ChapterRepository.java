package neu.com.repository;

import neu.com.model.Chapter;
import neu.com.model.ZoomClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Optional<Chapter> findByChapterTitleAndChapterIdNot(String chapterTitle, Long chapterId);

    Optional<Chapter> findByChapterTitle(String chapterTitle);
}
