package com.ssafy.apt.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.apt.model.dto.AptDto;
import com.ssafy.apt.model.dto.CityDto;


public interface AptDao {

    public int saveApt(AptDto aptDto);
    public List<AptDto> getApt(String DEAL_YMD, String regcode);
    int addCity(CityDto city) throws SQLException;
    List<CityDto> getCityList() throws SQLException;

}
