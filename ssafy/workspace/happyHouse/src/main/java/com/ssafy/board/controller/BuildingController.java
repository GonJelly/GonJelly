package com.ssafy.board.controller;

import com.ssafy.Util.PageNavigation;
import com.ssafy.Util.SizeConstant;
import com.ssafy.board.model.BuildInfoDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BuildService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

@Controller
@RequestMapping("/build")
public class BuildingController {
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    @Autowired
    private BuildService buildService;

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchApt(@RequestParam(required = false) Map<String,Object> map) throws Exception {
        logger.debug("BuildingController searchApt map : {}",map);
        // 아파트 조회
        List<BuildInfoDto> list = buildService.searchApt(map);
        // 아파트 페이징
        PageNavigation pageNav = buildService.makePageNavigation(map);

        Map<String,Object> result = new HashMap<>();
        result.put("buildList",list);
        result.put("pageNav",pageNav);
        result.put("params",map);

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }
    @RequestMapping("/detail/{dealNo}")
    public String detail(@PathVariable String dealNo, Model model) throws Exception{

        logger.debug("BuildingController detail() dealNo : {}", dealNo);
        BuildInfoDto build = buildService.dealDetail(dealNo);
        model.addAttribute("build",build);
        return "build/buildDetail";
    }
}
