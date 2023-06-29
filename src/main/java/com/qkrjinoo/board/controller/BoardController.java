package com.qkrjinoo.board.controller;

import com.qkrjinoo.board.dto.BoardDto;
import com.qkrjinoo.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // 해당 클래스가 컨트롤러임을 나타내는 어노테이션
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")    // 게시글 목록을 보여주는 페이지로 이동하는 핸들러 메소드
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);   // Model은 Spring의 MVC모델 객체로 뷰에 데이터를 전달하는데 사용
        return "board/list.html";
    }

    @GetMapping("/post")    // 게시글 작성 페이지로 이동하는 핸들러 메소드
    public String post() {
        return "board/post.html";
    } // HTTP POST 요청을 처리하는 메소드 "/post" 경로로 접근 시 게시글 작성을 처리하고, 작성된 게시글을 저장한다.

    @PostMapping("/post")   // 게시글 작성을 처리하는 핸들러 메소드
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    } // @GetMapping("/post/{id}"): HTTP GET 요청을 처리하는 메소드 "/post/{id}" 경로로 접근 시 특정 게시글의 상세 정보를 보여주는 페이지로 이동한다.

    @GetMapping("/post/{id}")   // 특정 게시글의 상세 정보를 보여주는 페이지로 이동하는 핸들러 메소드
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")  // 게시글 수정 페이지로 이동하는 핸들러 메소드
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    @PutMapping("/post/edit/{id}")  // 게시글 수정을 처리하는 핸들러 메소드
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")    // 게시글 삭제를 처리하는 핸들러 메소드
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }
}