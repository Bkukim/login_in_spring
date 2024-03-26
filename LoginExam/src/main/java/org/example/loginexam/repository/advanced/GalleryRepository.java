package org.example.loginexam.repository.advanced;

import org.example.loginexam.model.entity.advanced.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.advanced
 * fileName : GalleryRepository
 * author : PC
 * date : 2024-03-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-25         PC          최초 생성
 */

@Repository
public interface GalleryRepository extends JpaRepository<Gallery,String> {

    @Query(
            value = "SELECT * FROM TB_GALLERY\n" +
                    "WHERE GALLERY_TITLE LIKE '%'|| :galleryTitle ||'%'" +
                    "AND DELETE_YN = 'N'", // 소프트 삭제를 할때는 "AND DELETE_YN = 'N'"을 해줘야한다. 지워진 애는 보이면 안되니까...
            countQuery = "SELECT count (*) FROM TB_GALLERY\n" +
                    "WHERE GALLERY_TITLE LIKE '%'|| :galleryTitle ||'%'"+
                    "AND DELETE_YN = 'N'",
            nativeQuery = true
    )
    public Page<Gallery> findAllByGalleryTitleContaining(@Param("galleryTitle") String galleryTitle, Pageable pageable);
}
