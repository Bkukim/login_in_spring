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
 * fileName : FildDb
 * author : PC
 * date : 2024-03-22
 * description : 파일업로드 엔티티 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-22         PC          최초 생성
 */
// todo : jpa 어노테이션
@Entity
@Table(name = "TB_FILE_DB")
@DynamicUpdate
@DynamicInsert
// todo : lombok 어노테이션
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
// todo : soft delete jpa 어노테이션 - jpa 에서 soft delete를 편하게 할수 있도록 제공하는 어노테이션들이다.
@Where(clause = "DELETE_YN = 'N'") // 소프트 딜리트를 위한 조건절에 들어갈 내용을 넣어주는 어노테이션이다.
// N인 애들만 보여줄 것이므로 다음과 같이 했다.
@SQLDelete(sql = "UPDATE TB_FILE_DB " +
        "SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') " +
        "WHERE UUID = ?") // 소프트 삭제가 진행되면 진행할 대체 sql문을 적어주는 곳이다.
// 소프트 삭제시에는 delete가 아니고 update로 y값을 해주어야하기때문에 위와 같이 작성해준다.
public class FileDb extends BaseTimeEntity2 {

    @Id
    private String uuid; // 자바에서 생성할 기본키
    private String fileTitle;
    private String fileContent;
    private String fileName;

    @Lob // 컬럼에 이미지를 저장하는 DB자료형이 blob자료형이고, 자바에서는 byte를 DB에서 blob라고 해주는 역할을 한다.
    private byte[] fileData; // DB에서 blob 타입은 java에서는 byte로 해주면된다.
    private String fileUrl;

}
