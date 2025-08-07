package com.example.board.controller;

import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.CreateBoardRequestDto;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> save(@RequestBody CreateBoardRequestDto createBoardRequestDto) {

        BoardResponseDto savedBoard = boardService.save(
                createBoardRequestDto.getTitle(),
                createBoardRequestDto.getContents(),
                createBoardRequestDto.getUsername()
        );

        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {
        List<BoardResponseDto> boardResponseDto = boardService.findAll();

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }
}
