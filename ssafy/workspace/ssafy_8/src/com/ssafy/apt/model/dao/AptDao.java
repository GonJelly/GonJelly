package com.ssafy.apt.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ssafy.apt.model.dto.*;


public interface AptDao {

    public List<HouseInfoDto> getApt(String DEAL_YMD, String regcode);
    Map<String, Integer> getCity() throws SQLException;
    Map<String, String> getGungu(String sidoCode) throws SQLException;
    List<CityDto> getCityList() throws SQLException;
    List<DongDto> getDong(String Sigungu_code) throws SQLException;
    RoadDto getRoad(String Road_code, String Road_Seq) throws SQLException;
    int addCity(CityDto city) throws SQLException;
    int addDong(List<DongDto> dong) throws SQLException;
    //    void commit() throws SQLException;
    int addHouse(List<HouseInfoDto> list) throws SQLException;
    int addRoad(Set<RoadDto> set) throws SQLException;
}
