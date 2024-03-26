package org.example.loginexam.service.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.loginexam.model.entity.advanced.Gallery;
import org.example.loginexam.repository.advanced.GalleryRepository;
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
 * fileName : GalleryService
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
@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    @Autowired
    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    public Page<Gallery> findAllByGalleryTitleContaining(String galleryTitle, Pageable pageable){
        return galleryRepository.findAllByGalleryTitleContaining(galleryTitle, pageable);
    }

    public Gallery save(String uuid,
                        String galleryTitle,
                        MultipartFile file){
        Gallery gallery1 = null;
            try {//file.getBytes를 사용하려면 try가 필요함
                if (uuid == null) {
                    // uuid만들기
                    String tempUuid = UUID.randomUUID().toString().replace("-", "");

                    // 다운로드에 사용할 url만들기
                    String fileDownload = ServletUriComponentsBuilder
                            .fromCurrentContextPath() // 스프링 기본 url localhost:8000
                            .path("/advanced/gallery/") // 우리가 만든 url
                            .path(tempUuid)
                            .toUriString();

                    // 생성자에 만든 url넣어주기
                    Gallery gallery = new Gallery(tempUuid,
                            galleryTitle,
                            file.getOriginalFilename(),
                            file.getBytes(),
                            fileDownload);
                     gallery1 = galleryRepository.save(gallery);
                }else {
                    String fileDownload = ServletUriComponentsBuilder
                            .fromCurrentContextPath() // 스프링 기본 url localhost:8000
                            .path("/advanced/gallery/") // 우리가 만든 url
                            .path(uuid)
                            .toUriString();

                    // 생성자에 만든 url넣어주기
                    Gallery gallery = new Gallery(uuid,
                            galleryTitle,
                            file.getOriginalFilename(),
                            file.getBytes(),
                            fileDownload);
                    gallery1 = galleryRepository.save(gallery);
                }
            }catch (Exception e){
                log.debug(e.getMessage());
             }
                return gallery1;

    }

    public Optional<Gallery> findById(String uuid){
        return galleryRepository.findById(uuid);
    }

    public boolean deleteBiId(String uuid){
        if (galleryRepository.existsById(uuid)) {
            galleryRepository.deleteById(uuid);
            return true;
        }else {
            return false;
        }
    }

}
