package com.ssafy.board.controller;

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

    private static final int LIMITEND = 6;
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    @Autowired
    private BuildService buildService;

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchApt(@RequestParam(required = false) Map<String,Object> map) throws Exception {

        int year = map.get("year") == null ? Year.now().getValue() : (int) map.get("year");
        int count = buildService.dealCount(year);

        map.put("year",Year.now().getValue());
        map.put("start", (int) ( Math.random() * ( count < LIMITEND ? 0 : count ) ));

        List<BuildInfoDto> list = buildService.searchApt(map);

        return new ResponseEntity<List<BuildInfoDto>>(list, HttpStatus.OK);
    }
    @RequestMapping("/detail/{dealNo}")
    public String detail(@PathVariable String dealNo, Model model) throws Exception{

        logger.debug("BuildingController detail() dealNo : {}", dealNo);
        BuildInfoDto build = buildService.dealDetail(dealNo);
        model.addAttribute("build",build);
        return "build/buildDetail";
    }
}
