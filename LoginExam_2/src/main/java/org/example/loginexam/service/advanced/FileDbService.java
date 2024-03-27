package org.example.loginexam.service.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.loginexam.model.entity.advanced.FileDb;
import org.example.loginexam.repository.advanced.FileDbRepository;
import org.example.loginexam.model.entity.advanced.FileDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;


/**
 * packageName : org.example.jpaexam.service.advanced
 * fileName : FileDbService
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
@Slf4j
@Service
public class FileDbService {

    private final FileDbRepository fileDbRepository;

    @Autowired
    public FileDbService(FileDbRepository fileDbRepository) {
        this.fileDbRepository = fileDbRepository;
    }

    public Page<FileDb> findAllByFileTitleContaining(String fileTitle, Pageable pageable){
        Page<FileDb> page = fileDbRepository.findAllByFileTitleContaining(fileTitle, pageable);
        return page;
    }

    // todo 저장/수정 : save
    //      1) insert : 기본키 없으면
    //          1-1) uuid(기본키) 생성
    //          1-2) 다운로드 url생성
    //              a)현재 기본 주소: http://localhost:8000
    //              b)추가 주소 붙임: /advanced/fileDb를 붙이자.
    //              c) 파일명(uuid) : 다운로드 파일명은 유일해야한다. uuid는 세상에서 하나만 존재하는 번호를 자바에서 생성해주는 것이다.
    //              d)최종 url => http://localhost:8000/advanced/fileDb/xxxx
    //         1-3) FileDb 객체에 넣어서(생성자, setter) 저장(save)

    public FileDb save (String uuid, // name은 왜 안 넣어주는가? data는?
                        String fileTitle,
                        String fileContent,
                        MultipartFile file // 파일 업로드 클래스로, 이 형태로 파일이 이동되므로 이 형태로 파일을  받아야한다는 거을 정해주는 것.
                        ){ // 파일을 만들때는 예외처리가 필요하다. 그리고 매개변수를 객체로 받으면 복잡할 수 있어서 변수로 받는다.
                        FileDb fileDb2 = null;
                        try{
                            if (uuid == null) {
                                // todo : 기본키가 없을때 : insert
                                //      1-1) uuid 생성하기
                                String tmpUuid = UUID.randomUUID().toString().replace("-",""); // uuid 만드는 방법
                                // xxxx-xxxx-xxxx-xx...이런 형태로 만들어진다. 근데 "-"가 보기 좋지 않으니 없애보자. replace 함수 이용

                                // todo  1-2) 다운로드 url 생성 -> 자바함수를 이용 ※여기서 다운로드란 jsp이 spring에서 이미지를 다운받아 가져오는 것.
                                String fileDownload = ServletUriComponentsBuilder
                                        .fromCurrentContextPath()// 스프링 서버 기본 주소 : localhost:8000
                                        .path("/advanced/fileDb/") // 추가 경로 넣기 : /advanced/fileDb
                                        .path(tmpUuid) // uuid를 url 제일 마지막에 넣어주기
                                        .toUriString(); // 위의 url을 하나로 합쳐주는 함수 http://localhost:8000/advanced/fileDb/xxxx 가 된다.

                                // todo  1-3) 생성자에 만든 url넣어주기
                                FileDb fileDb = new FileDb(tmpUuid,
                                        fileTitle,
                                        fileContent,
                                        file.getOriginalFilename(), // 파일 업로드 당시 파일명
                                        file.getBytes(), // 파일 데이터
                                        fileDownload); // 우리가 만든 url
                                fileDb2 = fileDbRepository.save(fileDb);
                            }else {
                                // 기본키가 있을때 : update
                                // todo  1-2) 다운로드 url 생성 -> 자바함수를 이용 ※여기서 다운로드란 jsp이 spring에서 이미지를 다운받아 가져오는 것.
                                String fileDownload = ServletUriComponentsBuilder
                                        .fromCurrentContextPath()// 스프링 서버 기본 주소 : localhost:8000
                                        .path("/advanced/fileDb/") // 추가 경로 넣기 : /advanced/fileDb
                                        .path(uuid) // uuid를 url 제일 마지막에 넣어주기
                                        .toUriString(); // 위의 url을 하나로 합쳐주는 함수 http://localhost:8000/advanced/fileDb/xxxx 가 된다.

                                // todo  1-3) 생성자에 만든 url넣어주기
                                FileDb fileDb = new FileDb(uuid,
                                        fileTitle,
                                        fileContent,
                                        file.getOriginalFilename(),
                                        file.getBytes(),
                                        fileDownload);
                                fileDb2 = fileDbRepository.save(fileDb);
                            }
                        }catch (Exception e){
                            log.debug(e.getMessage());

                        }
        return fileDb2;
    }

    public Optional<FileDb> findById(String uuid){
        Optional<FileDb> fileDb = fileDbRepository.findById(uuid);
        return fileDb;
    }

    public boolean removeById(String uuid){
        if (fileDbRepository.existsById(uuid)) {
            fileDbRepository.deleteById(uuid);
            return true;
        }else {
            return false;
        }
    }
}
