package com.qkrjinoo.board.domain.repository;

import com.qkrjinoo.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
