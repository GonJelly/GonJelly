package com.ssafy.apt.model.service;


import com.ssafy.apt.model.dto.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ssafy.apt.model.dao.AptDao;
import com.ssafy.apt.model.dao.AptDaoImpl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.geom.Area;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AptServiceImpl implements AptService{
    private static AptDao dao;
    private static AptServiceImpl instance = new AptServiceImpl();

    private AptServiceImpl() {
        dao = AptDaoImpl.getInstance();
    }

    public static AptServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<HouseInfoDto> getApt(String DEAL_YMD, String regcode) {
        return dao.getApt(DEAL_YMD, regcode);
    }

    private static Element getElement(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document parser = builder.parse(url);
        Element element = parser.getDocumentElement();
        element.toString();
        return element;
    }

    @Override
    public int addCity(CityDto city) throws Exception {
        return dao.addCity(city);
    }

    @Override
    public List<CityDto> getCityList() throws Exception {
        return dao.getCityList();
    }

    @Override
    public int addDong(List<DongDto> dong) throws Exception {
        return dao.addDong(dong);
    }

    @Override
    public List<DongDto> getDong(String SigunguCode) throws Exception {
        return dao.getDong(SigunguCode);
    }

    @Override
    public int addRoad(Set<RoadDto> set) throws Exception {
        if( set.size() == 0 ) return 0;
        return dao.addRoad(set);
    }

    @Override
    public int addHouse(List<HouseInfoDto> list) throws Exception {
        return dao.addHouse(list);
    }

    @Override
    public boolean isVailable(String Road_Code, String Road_Seq) throws Exception {
        RoadDto isCheck = dao.getRoad(Road_Code,Road_Seq);
        if( isCheck == null ) return true;
        return false;
    }

    @Override
    public Map<String, Object> getCityDong() throws Exception {
        return null;
    }

    //    @Override
//    public RoadDto getRoad(String Road_Code, String Road_Seq) throws Exception {
//        return dao.getRoad(Road_Code,Road_Seq);
//    }
}
