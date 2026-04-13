package com.busanit401.restapibootflutterreact.service;

import com.busanit401.restapibootflutterreact.dto.*;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TodoService {
    Long register(TodoDTO todoDTO);
    TodoDTO read(Long tno);
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
    CursorPageResponseDTO<TodoDTO> list2(CursorPageRequestDTO pageRequestDTO);
    Long getMaxTno();
    void remove(Long tno);
    void modify(TodoDTO todoDTO);
}
