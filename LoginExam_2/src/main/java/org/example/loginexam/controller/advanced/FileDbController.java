package org.example.loginexam.controller.advanced;

import lombok.extern.slf4j.Slf4j;

import org.example.loginexam.model.entity.advanced.FileDb;
import org.example.loginexam.service.advanced.FileDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

/**
 * packageName : org.example.jpaexam.controller.advanced
 * fileName : FileDbController
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
@Controller
@RequestMapping("/advanced")
public class FileDbController {

    private final FileDbService fileDbService;

    @Autowired

    public FileDbController(FileDbService fileDbService) {
        this.fileDbService = fileDbService;
    }

    @GetMapping("/fileDb")
    public String getFileAll(Model model,
                            @RequestParam(defaultValue = "") String fileTitle,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<FileDb> pageRes = fileDbService.findAllByFileTitleContaining(fileTitle, pageable);

        model.addAttribute("fileDb", pageRes.getContent());
        model.addAttribute("currentPage", pageRes.getNumber());
        model.addAttribute("totalItems", pageRes.getTotalElements());
        model.addAttribute("totalPages", pageRes.getTotalPages());

        //     공식 : 블럭 시작페이지 번호 = (Math.floor(현재페이지번호/1페이지당개수)) * 1페이지당개수
//     TODO: size -> 3
//       페이지번호 화면에 보이기 변수
        long pageNo = 4;
        long blockStartPage = (long) Math.floor((double) (pageRes.getNumber()) / pageNo) * pageNo;
        model.addAttribute("startPage", blockStartPage);              // 5) 블럭 시작페이지번호
//     공식 : 블럭 끝페이지 번호 = 블럭 시작페이지번호 + 1페이자당개수 - 1
//       TODO: size -> 3
        long blockEndPage = blockStartPage + pageNo - 1;
//        블럭 끝페이지 번호 >=  전체페이지번호 : 이 경우가 발생할 수 있음
//        블럭 끝페이지 번호 = 전체페이지번호 - 1 (값 보정)
        blockEndPage = (blockEndPage >= pageRes.getTotalPages()) ? pageRes.getTotalPages() - 1 : blockEndPage;
        //        TODO: blockEndPage < 0 이면 blockEndPage = 0 고정 : blockEndPage 음수이면 jsp 반복문에서 에러발생(버그 수정)
        blockEndPage = (blockEndPage < 0) ? 0 : blockEndPage;

        model.addAttribute("endPage", blockEndPage);
//        TODO: fileTitle -> jsp 전송
        model.addAttribute("fileTitle", fileTitle);

        return "advanced/fileDb/fileDb_all.jsp";
    }

    @GetMapping("/fileDb/addition")
    public String addFileDb(){
        return "advanced/fileDb/add_fileDb.jsp";
    }

    //          2) 저장 버튼 클릭시(파일업로드) 실행될 함수
//    insert -> post 방식 -> @PostMapping
//    파일업로드 : 쿼리스트링 : @RequestParam 사용
    @PostMapping("/fileDb/add")
    public RedirectView createFileDb(
            @RequestParam(defaultValue = "") String fileTitle,
            @RequestParam(defaultValue = "") String fileContent,
            @RequestParam MultipartFile image // 파일 전송 : MultipartFile
    ) {
        try {
//            DB 저장 서비스 함수 실행 : insert 시 uuid 없음(null)
            fileDbService.save(null, fileTitle, fileContent, image);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return new RedirectView("/advanced/fileDb");
    }


    // 파일 다운로드(전송) 함수
    // => jsp에서 파일 다운로드 url 클릭 또는 img 태그 넣으면 이 함수에서 실제적으로 파일을 전송해준다.
    // return 값이 json데이터로 전송해야하기에 json이다.

    @GetMapping("/fileDb/{uuid}") // tempUuid와 같은 url값이다.
    @ResponseBody // josn데이터로 리턴하게 해주는 어노테이션이다.
    public ResponseEntity<byte[]> findDownload(@PathVariable String uuid){ // 특정 파일을 보내주어야하므로 uuid를 변수로 받아서 byid로 찾아줘야함
        // todo : db 상세조회 서비스함수 실행 : uuid

        FileDb fileDb = fileDbService.findById(uuid).get(); // 특정 파일을 보내야하므로 이렇게 객체를 id로 찾아내야함 일단.

        return ResponseEntity.ok() // jsp를 전송할때 신호와 함께 보내주는 함수이다.
                // jsp에서는 받는 데이터가 괜찮은 데이터인지를 알 지 못한다. 그래서 ok 신호를 주면 괜찮은데이터ㄹ라는 것을 알려준다.
                .header(HttpHeaders.CONTENT_DISPOSITION, // 전송하는 파일의 타입인데 첨부파일형식은 위의 것을 쓴다.
                        "attachment; filename=\"" + fileDb.getFileName() + "\"") // json파일에도 header body 부분이 존재한다. 헤더에는 파일의 형태, 바디에는 내용이 들어간다.
                // 파일전송에는 일반 파일 전달과는 다르게
                // 헤터 :
                // 1) 파일 형태 :CONTENT_DISPOSITION
                // 2) 첨부파일 정보 : attachment; filename="xxx.jpg"
                .body(fileDb.getFileData());
                // 바디 :
                // 1) 실제 이미지 파일 데이터
    }
    // 업데이트 함수
    @GetMapping("/fileDb/edition/{uuid}")
    public String editionFileDb(Model model, @PathVariable String uuid){
        Optional<FileDb> fileDb = fileDbService.findById(uuid);
        model.addAttribute("fileDb" , fileDb.get());
        return "/advanced/fileDb/edit_fileDb.jsp";
    }

    @PutMapping("/fileDb/edit/{uuid}")
    public RedirectView updateFileDb(@PathVariable String uuid,
                                     @RequestParam(defaultValue = "") String fileTitle,
                                     @RequestParam(defaultValue = "") String fileContent,
                                     @RequestParam MultipartFile image){
        try{
            fileDbService.save(uuid, fileTitle, fileContent ,image);
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/advanced/fileDb");
    }
     // 삭제함수
    @DeleteMapping("/fileDb/delete/{uuid}")
    public RedirectView deleteFileDb(@PathVariable String uuid){
        fileDbService.removeById(uuid);
        return new RedirectView("/advanced/fileDb");
    }
}

