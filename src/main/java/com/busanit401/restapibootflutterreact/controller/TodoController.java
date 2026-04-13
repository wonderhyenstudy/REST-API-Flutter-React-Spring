package com.busanit401.restapibootflutterreact.controller;

import com.busanit401.restapibootflutterreact.dto.PageRequestDTO;
import com.busanit401.restapibootflutterreact.dto.PageResponseDTO;
import com.busanit401.restapibootflutterreact.dto.TodoDTO;
import com.busanit401.restapibootflutterreact.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
@Log4j2
public class TodoController {
    private final TodoService todoService;

    // 등록
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {
        Long tno = todoService.register(todoDTO);
        return Map.of("tno", tno);
    }

    // 조회
    @GetMapping("/{tno}")
    public TodoDTO read(@PathVariable Long tno) {
        return todoService.read(tno);
    }

    // 목록 + 검색 + 페이징
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        return todoService.list(pageRequestDTO);
    }

    // 삭제
    @DeleteMapping("/{tno}")
    public Map<String, String> delete(@PathVariable Long tno) {
        todoService.remove(tno);
        return Map.of("result", "success");
    }

    // 수정 (title, dueDate, complete만 변경 가능)
    @PutMapping(value = "/{tno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> modify(@PathVariable Long tno, @RequestBody TodoDTO todoDTO) {
        todoDTO.setTno(tno);
        todoService.modify(todoDTO);
        return Map.of("result", "success");
    }
}
