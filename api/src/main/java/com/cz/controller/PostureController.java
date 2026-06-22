package com.cz.controller;

import com.cz.service.PostureService;
import com.cz.vo.PostureVo;
import com.cz.vo.ResultVo;
import com.cz.core.EventWebSocketHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/posture")
public class PostureController {

    @Autowired
    private PostureService postureService;
    @Autowired
    private EventWebSocketHandler postureWebSocketHandler;

    @GetMapping("/list")
    public ResultVo getPosture() {
        return postureService.getPosture();
    }

    @GetMapping("/{id}")
    public ResultVo getPostureById(@PathVariable Integer id) {
        return postureService.getPostureById(id);
    }

    @PostMapping("/create")
    public ResultVo createPosture(@RequestBody PostureVo postureVo, HttpServletRequest request) {
        return postureService.createPosture(postureVo);
    }

    @PutMapping("/update/{id}")
    public ResultVo updatePosture(@PathVariable Integer id, @RequestBody PostureVo postureVo) {
        return postureService.updatePosture(id, postureVo);
    }

    @DeleteMapping("/delete/{id}")
    public ResultVo deletePosture(@PathVariable Integer id) {
        return postureService.deletePosture(id);
    }

    @PostMapping("/decodeAndCreate")
    public ResultVo decodeAndCreate(@RequestBody Map<String, Object> data) throws Exception {
        ResultVo resultVo = postureService.decodeAndCreate(data);
        if (resultVo.getCode() == 0) {
            postureWebSocketHandler.sendPostureData(1);
        }
        return resultVo;
    }

}