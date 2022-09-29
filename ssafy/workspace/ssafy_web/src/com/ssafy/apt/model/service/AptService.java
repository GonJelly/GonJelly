package com.ssafy.apt.model.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.ssafy.apt.model.dto.*;

public interface AptService {

    List<HouseInfoDto> getApt(String DEAL_YMD, String regcode);
    int addCity(CityDto city) throws Exception;
    List<CityDto> getCityList() throws Exception;
    int addDong(List<DongDto> dong) throws Exception;
    List<DongDto> getDong(String SigunguCode) throws Exception;
    int addRoad(Set<RoadDto> set) throws Exception;
    int addHouse(List<HouseInfoDto> list) throws Exception;
    boolean isVailable(String Road_Code,String Road_Seq) throws Exception;
    Map<String, Integer> getCity() throws Exception;
    Map<String, String> getGungu(String sidoCode) throws Exception;
}
