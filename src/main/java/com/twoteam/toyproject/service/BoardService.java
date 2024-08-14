package com.twoteam.toyproject.service;

import com.twoteam.toyproject.entity.Board;
import com.twoteam.toyproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void save(Board board) {
        boardRepository.save(board);
    }
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
