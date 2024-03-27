package org.example.loginexam.repository.advanced;

import org.example.loginexam.model.entity.advanced.FileDb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.advanced
 * fileName : FileDbRepository
 * author : PC
 * date : 2024-03-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-22         PC          최초 생성
 */
@Repository
public interface FileDbRepository extends JpaRepository<FileDb, String> {
    @Query(
            value = "SELECT * FROM TB_FILE_DB\n" +
                    "WHERE FILE_TITLE LIKE '%'|| :fileTitle ||'%'" +
                    "AND DELETE_YN = 'N'", // 소프트 삭제를 할때는 "AND DELETE_YN = 'N'"을 해줘야한다. 지워진 애는 보이면 안되니까...
            countQuery = "SELECT count (*) FROM TB_FILE_DB\n" +
                    "WHERE FILE_TITLE LIKE '%'|| :fileTitle ||'%'"+
                    "AND DELETE_YN = 'N'",
            nativeQuery = true
    )
    public Page<FileDb> findAllByFileTitleContaining(@Param("fileTitle") String fileTitle, Pageable pageable);

}
