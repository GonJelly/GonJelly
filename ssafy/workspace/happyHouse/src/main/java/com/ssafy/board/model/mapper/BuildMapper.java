package com.ssafy.board.model.mapper;

import com.ssafy.board.model.BuildInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BuildMapper {

    // 아파트 조회하기
    List<BuildInfoDto> searchApt(Map<String,Object> map) throws SQLException;
    List<BuildInfoDto> searchWord(Map<String,Object> map) throws SQLException;
    // 아파트 상세조회하기
    BuildInfoDto dealDetail(String dealNo) throws SQLException;
    // 아파트 거래회수( 현재년도 기준 )
    int getTotalBuildDeal(Map<String,Object> param) throws SQLException;
}
