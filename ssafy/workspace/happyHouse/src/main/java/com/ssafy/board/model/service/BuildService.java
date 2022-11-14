package com.ssafy.board.model.service;

import com.ssafy.board.model.BuildInfoDto;

import java.util.List;
import java.util.Map;

public interface BuildService {
    List<BuildInfoDto> searchApt(Map<String, Object> map) throws Exception;
    int dealCount(int year) throws Exception;
    BuildInfoDto dealDetail(String dealNo) throws Exception;
}
