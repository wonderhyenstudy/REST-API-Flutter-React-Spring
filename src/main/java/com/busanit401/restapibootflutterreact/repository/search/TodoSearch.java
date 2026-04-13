package com.busanit401.restapibootflutterreact.repository.search;

import com.busanit401.restapibootflutterreact.dto.CursorPageRequestDTO;
import com.busanit401.restapibootflutterreact.dto.PageRequestDTO;
import com.busanit401.restapibootflutterreact.dto.TodoDTO;
import org.springframework.data.domain.Page;

public interface TodoSearch {
    Page<TodoDTO> list(PageRequestDTO pageRequestDTO);
    Page<TodoDTO> list2(CursorPageRequestDTO pageRequestDTO);
}