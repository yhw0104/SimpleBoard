package com.hyunwoo.board.controller;

import com.hyunwoo.board.dto.BoardDto;
import com.hyunwoo.board.service.BoardService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller    // 컨트롤러임을 명시하는 어노테이션
                // MVC에서 컨트롤러로 명시된 클래스의 메서드들은 return 값으로 템플릿 경로를 작성하거나, redirect를 해줘야 합니다.
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    //게시글 목록
    @GetMapping("/")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list.html";
    }


    // 게시글 추가
    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }

    //게시글 DB Insert 함수(wirte 메서드)
    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/";
    }

    // 게시글 상세조회 페이지
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail.html";
    }

    //게시글 수정 페이지
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    // 게시글 수정
    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }

    // 게시글 삭제
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";    
    }

    // 게시글 검색
    // 특별한 것은 없고, 클라이언트에서 넘겨주는 keyword를 검색어로 활용
    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list.html";
    }

}