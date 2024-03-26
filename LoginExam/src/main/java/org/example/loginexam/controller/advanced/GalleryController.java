package org.example.loginexam.controller.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.loginexam.model.entity.advanced.Gallery;
import org.example.loginexam.service.advanced.GalleryService;
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
 * fileName : GallleryController
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
@Slf4j
@Controller
@RequestMapping("/advanced")
public class GalleryController {

    private final GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/gallery")
    public String findAllByGalleryTitleContaining(Model model,
                             @RequestParam(defaultValue = "") String galleryTitle,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Gallery> pageRes = galleryService.findAllByGalleryTitleContaining(galleryTitle, pageable);

        model.addAttribute("gallery", pageRes.getContent());
        model.addAttribute("currentPage", pageRes.getNumber());
        model.addAttribute("totalItems", pageRes.getTotalElements());
        model.addAttribute("totalPages", pageRes.getTotalPages());

        //     공식 : 블럭 시작페이지 번호 = (Math.floor(현재페이지번호/1페이지당개수)) * 1페이지당개수
//     TODO: size -> 3
//       페이지번호 화면에 보이기 변수
        long pageNo = 3;
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
        model.addAttribute("galleryTitle", galleryTitle);

        return "advanced/gallery/gallery_all.jsp";
    }

    @GetMapping("/gallery/addition")
    public String addGallery(){
        return "/advanced/gallery/add_gallery.jsp";
    }

    @PostMapping("/gallery/add")
    public RedirectView createGallery(@RequestParam String galleryTitle,
                                      @RequestParam MultipartFile image){
        try{
            galleryService.save(null, galleryTitle, image);
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/advanced/gallery");
    }

    @GetMapping("/gallery/{uuid}")
    @ResponseBody // json데이터로 리턴하게 해주는 어노테이션
    public ResponseEntity<byte[]> fileDownload(@PathVariable String uuid){
       Gallery gallery = galleryService.findById(uuid).get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + gallery.getGalleryFileName() + "\"")
                .body(gallery.getGalleryData());
    }

    @GetMapping("/gallery/edition/{uuid}")
    public String editionGallery(Model model, @PathVariable String uuid){
        Optional<Gallery> gallery =  galleryService.findById(uuid);
        model.addAttribute("gallery", gallery.get());
        return "/advanced/gallery/edit_gallery.jsp";
    }

    @PutMapping("/gallery/edit/{uuid}")
    public RedirectView updateGallery(@PathVariable String uuid,
                                      @RequestParam (defaultValue = "") String galleryTitle,
                                      @RequestParam MultipartFile file ){
        try {
            galleryService.save(uuid, galleryTitle, file );
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/advanced/gallery");
    }

    @DeleteMapping("/gallery/delete/{uuid}")
    public RedirectView deleteGallery(@PathVariable String uuid){
        galleryService.deleteBiId(uuid);
        return new RedirectView("/advanced/gallery");
    }
}
