package com.qkrjinoo.board.service;

import com.qkrjinoo.board.domain.entity.Board;
import com.qkrjinoo.board.domain.repository.BoardRepository;
import com.qkrjinoo.board.dto.BoardDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional  // 게시글 저장 메소드
    public Long savePost(BoardDto boardDto) {
        // BoardDto를 Board 엔티티로 변환하여 저장하고, 저장된 게시글의 id를 반환합니다.
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional  // 게시글 목록 조회 메소드
    public List<BoardDto> getBoardList() {
        // 모든 게시글을 조회한다.
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        // 조회된 각각의 게시글을 BoardDto로 변환하고 리스트에 추가한다.
        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        // BoardDto 리스트 반환
        return boardDtoList;
    }

    // 특정 게시글 조회 메소드
    @Transactional
    public BoardDto getPost(Long id) {
        // 주어진 id에 해당하는 게시글을 조회한다.
        Board board = boardRepository.findById(id).get();

        // 조회된 게시글을 BoardDto로 변환하고 반환한다.
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
        return boardDto;
    }

    // 게시글 삭제 메소드
    @Transactional
    public void deletePost(Long id) {
        // 주어진 id에 해당하는 게시글을 삭제한다.
        boardRepository.deleteById(id);
    }
}
