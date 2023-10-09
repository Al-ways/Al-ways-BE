package com.project.always.bar.controller;

import com.project.always.bar.dto.BarDTO;
import com.project.always.bar.mapper.BarMapper;
import com.project.always.bar.service.BarService;
import com.project.always.utils.HttpResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.project.always.utils.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bar")
@Controller
public class BarController {

    private final BarService barService;
    private final BarMapper barMapper;
    @GetMapping("/allbar")
    public HttpResponseEntity.ResponseResult<List<BarDTO>> getList(){
        return success(barMapper.toDtoList(barService.findAll()));
    }

}
