package com.project.always.bar.controller;

import com.project.always.bar.dto.UserBarRequestDTO;
import com.project.always.bar.service.UserBarService;
import com.project.always.utils.HttpResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.project.always.utils.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/userbar")
public class UserBarController {
    private final UserBarService userBarService;

    @PostMapping("/like")
    public HttpResponseEntity.ResponseResult<String> insert(@RequestBody @Valid UserBarRequestDTO userBarRequestDTO) throws Exception {
        userBarService.insert(userBarRequestDTO);
        return success();
    }

    @DeleteMapping("/unlike")
    public HttpResponseEntity.ResponseResult<String> delete(@RequestBody @Valid UserBarRequestDTO userBarRequestDTO) {
        userBarService.delete(userBarRequestDTO);
        return success();
    }
}
