package org.example.loginexam.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.loginexam.model.entity.basic.Dept;
import org.example.loginexam.service.basic.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

/**
 * packageName : org.example.jpaexam.controller.basic
 * fileName : DeptController
 * author : GGG
 * date : 2024-03-19
 * description : 부서 컨트롤러
 * 요약 :
 *      컨트롤러 : 함수에 url 달수 있고, return 값은 jsp 보낼수 있음
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/basic")
public class DeptController {

//    서비스 클래스 가져오기
    @Autowired
    DeptService deptService;

//    전체 조회 + like 검색 + 페이징 처리
    @GetMapping("/dept")
    public String getDeptAll(
            @RequestParam(defaultValue = "") String dname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page,size);
//        DB 전체 조회 서비스 함수 실행
        Page<Dept> pageRes = deptService.findAllByDnameContaning(dname, pageable);
//        결과를 jsp 전송
        // page객체에는 여러 함수가 많아서 다 보낼 필요없고 사용할 6가지 정보 것만 보내자.
        model.addAttribute("dept",pageRes.getContent());                        // 1. 부서정보 - 페이지 반복문 돌릴때 사용할 데이터
        model.addAttribute("currentPage",pageRes.getNumber());                  // 2. 현재페이지 번호
        model.addAttribute("totalItems",pageRes.getTotalElements());            // 3. 전체 행 건수
        model.addAttribute("totalPages",pageRes.getTotalPages());               // 4. 전체 페이지 개수
        // 공식 : 블럭 시작페이지 번호 = (Math.floor(현재페이지번호/1페이지당개수)) * 1페이지당개수
        long blockStartPage = (long) Math.floor((double) pageRes.getNumber()/size)*size;    // 5. 블럭 시작페이지번호
        // 공식 : 블럭 끝페이지 번호 = 블럭 시작페이지번호 + 1페이지당개수 - 1
        model.addAttribute("startPage", blockStartPage);
        long blockEndPage = blockStartPage + size -1;                                        // 6. 블럭 끝페이지 번호
        // 블럭 끝 페이지 번호가 전체페이지 번호와 다르게 될 수가 있다. 그래서 같게 보정해야함
        // 여기서 size를 더해주기 때문에 blockEndPage가 전체 페이지를 넘길 수 있다. 예를 들어 0부터 53까지의 페이지가 있다고하자. size는 5개로 설정
        // 그럼 마지막 블락의 blockStartPage는 50이 된다. 그런 blockEndPage 는 50 + 5 -1 = 54가 되어 더 커지게 된다.
        // -1을 해주는 이유는 우리가 원하는 값은 0부터 시작하는 get함수의 값이다. 그런데 이들과 달리 size는 1부터 시작하는 값이기 때문
        blockEndPage = (blockEndPage >= pageRes.getTotalPages())? pageRes.getTotalPages()-1 : blockEndPage; // 이 가능성이 나오는 경우는 젤 마지막 블록밖에 없기 때문에 이때만 전체(55) -1해주면 된다.
        blockEndPage = (blockEndPage<0)? 0: blockEndPage; // 검색을 했을경우 페이지가 하나도 없어서 total페이지가 0이 된다.
        // 그러면 blockEndpage가 음수가 되어 뒤의 jsp반복문에서 에러가 뜬다.
        // 그래서음수가 될 시 0으로 만들어주어야한다.
        model.addAttribute("endPage", blockEndPage);

        return "basic/dept/dept_all.jsp";
    }

    @GetMapping("/dept/{dno}")
    public String getDeptId(Model model, @PathVariable int dno){
        Optional<Dept> dept = deptService.findById(dno);
        model.addAttribute("dept", dept.get());
        return "basic/dept/dept_id.jsp";
    }

    @GetMapping("/dept/addition")
    public String additionDept(Model model){
        return "basic/dept/add_dept.jsp";
    }

    @PostMapping("/dept/save")
    public RedirectView addDept(Model model, @ModelAttribute Dept dept){
        deptService.save(dept);
        return new RedirectView("/basic/dept");
    }

    @GetMapping("/dept/edition/{dno}")
    public String editionDept(Model model,@PathVariable int dno){
        Optional<Dept> dept = deptService.findById(dno);
        model.addAttribute("dept", dept.get());
        return "basic/dept/edit_dept.jsp";
    }

    @PutMapping("/dept/edit/{dno}") // 수정이 완료되었을때 어떤 데이터(행)인지를 인식해야하므로 dno를 url에 넣어주어야한다.
    public RedirectView updateDept(@ModelAttribute Dept dept){
        deptService.save(dept);
        return new RedirectView("/basic/dept");
    }

    @DeleteMapping("/dept/delete/{dno}")
    public RedirectView deleteDept(@PathVariable int dno){

        deptService.deleteById(dno);
        return new RedirectView("/basic/dept");
    }
}
