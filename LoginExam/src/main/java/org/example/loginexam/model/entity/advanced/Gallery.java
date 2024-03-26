package org.example.loginexam.model.entity.advanced;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;
import org.example.loginexam.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.jpaexam.model.entity.advanced
 * fileName : Gallery
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
@Entity
@Table(name = "TB_GALLERY")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql = "UPDATE TB_GALLERY " +
        "SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') " +
        "WHERE UUID = ?")
public class Gallery extends BaseTimeEntity2 {

    @Id
    private String uuid; // 자바에서 생성할 기본키
    private String galleryTitle;
    private String galleryFileName;

    @Lob // 컬럼에 이미지를 저장하는 DB자료형이 blob자료형이고, 자바에서는 byte를 DB에서 blob라고 해주는 역할을 한다.
    private byte[] galleryData; // DB에서 blob 타입은 java에서는 byte로 해주면된다.
    private String galleryFileUrl;
}
