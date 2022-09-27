package com.ssafy.apt.model.service;

import java.util.List;

import com.ssafy.apt.model.dto.AptDto;
import com.ssafy.apt.model.dto.CityDto;

public interface AptService {

    void saveApt(int pageNo,int numOfRows,String LAWD_CD,String DEAL_YMD);
    List<AptDto> getApt(String DEAL_YMD, String regcode);
    int addCity(CityDto city) throws Exception;
    List<CityDto> getCityList() throws Exception;
}
