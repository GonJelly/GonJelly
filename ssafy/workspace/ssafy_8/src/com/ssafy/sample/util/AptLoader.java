package com.ssafy.sample.util;

import com.ssafy.apt.model.dto.CityDto;
import com.ssafy.apt.model.dto.HouseInfoDto;
import com.ssafy.apt.model.dto.RoadDto;
import com.ssafy.apt.model.service.AptService;
import com.ssafy.apt.model.service.AptServiceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AptLoader {

    private static final String pageNo = "1";
    private static final String numOfRows = "5";

    private static AptLoader instance = new AptLoader();
    private AptService service;
    private AptLoader() { service = AptServiceImpl.getInstance(); }
    public static AptLoader getInstance() { return instance; }
    
    public void load(){

        try {

            Date date = new Date();
            SimpleDateFormat simple = new SimpleDateFormat("yyyyMM");
//                System.out.println(simple.format(date));
            int current_year = Integer.parseInt(simple.format(date).substring(0,4));
            int current_month = Integer.parseInt(simple.format(date).substring(4,6));

            List<CityDto> list = service.getCityList();
            String DEAL_YMD = "";

            for(CityDto city : list) {

                for (int z = current_year; z > current_year-10; z--) {
                    if( current_year != z ) {
                        for( int m = 1; m <= 12; m++) {
                            String month = m < 10 ? "0" + m : String.valueOf(m);
                            DEAL_YMD = (z) + month;
//                            System.out.println(DEAL_YMD);
                            saveAPT(DEAL_YMD,city);
                        }
                    } else {
                        for( int m = 1; m <= current_month; m++) {
                            String month = m < 10 ? "0" + m : String.valueOf(m);
//                            System.out.println(DEAL_YMD);
                            DEAL_YMD = (z) + month;
                            saveAPT(DEAL_YMD,city);
                        }
                    }
                }

            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveAPT(String _DEAL_YMD, CityDto city) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
        url.append("?serviceKey=").append("BXsbI8mohhDdAvC7vE9mwtVm9rlr%2FAjpUw57Fr3shbk97SQG%2FULBIZpeRLx4O%2Fi4mwLqfvGiycnIxubRO%2Fus%2Bg%3D%3D");
        url.append("&pageNo=").append(pageNo);
        url.append("&numOfRows=").append(numOfRows);
        url.append("&LAWD_CD=").append(city.getSigungu_code().trim());
        url.append("&DEAL_YMD=").append(_DEAL_YMD);

        Element element = getElement(url.toString());
//            fileUpdate(element, _DEAL_YMD);    // 파일저장
        NodeList apts = element.getElementsByTagName("item");
        System.out.println(apts.getLength());
        Node current = null;

        List<HouseInfoDto> houseList = new ArrayList<>();
        Set<RoadDto> roadSet = new HashSet<>();

        for( int i = 0; i < apts.getLength(); i++) {

            current = apts.item(i);
            Element el = (Element) current;

            String code = el.getElementsByTagName("일련번호").item(0).getTextContent();
            String aptName = el.getElementsByTagName("아파트").item(0).getTextContent();
            String buildYear = el.getElementsByTagName("건축년도").item(0).getTextContent();
            String year = el.getElementsByTagName("년").item(0).getTextContent();
            String month = el.getElementsByTagName("월").item(0).getTextContent();
            String day = el.getElementsByTagName("일").item(0).getTextContent();
            String Deal_Amount = el.getElementsByTagName("거래금액").item(0).getTextContent().trim();
            String areaExUse = el.getElementsByTagName("전용면적").item(0).getTextContent();
            String floor = el.getElementsByTagName("층").item(0).getTextContent();
            String regcode = getTextContent(el,"법정동시군구코드");
            String eubmyudong = el.getElementsByTagName("법정동읍면동코드").item(0).getTextContent();
            String roadCode = el.getElementsByTagName("도로명코드").item(0).getTextContent();
            String bonbun = el.getElementsByTagName("법정동본번코드").item(0).getTextContent();
            String bubun = el.getElementsByTagName("법정동부번코드").item(0).getTextContent();
            String Jibun = el.getElementsByTagName("지번").item(0).getTextContent();

            HouseInfoDto house = new HouseInfoDto(code,aptName,buildYear,year,month,day
                    ,Deal_Amount,areaExUse,floor,regcode,eubmyudong,roadCode,bonbun,bubun,Jibun);

            String roadSeq = el.getElementsByTagName("도로명일련번호코드").item(0).getTextContent();
            String roadBonbun = el.getElementsByTagName("도로명건물본번호코드").item(0).getTextContent().trim();
            String roadBubun = el.getElementsByTagName("도로명건물부번호코드").item(0).getTextContent().trim();
            String roadBasement = getTextContent(el,"도로명지상지하코드");
//            System.out.println(roadBasement);
            String roadName = getTextContent(el,"도로명");
            RoadDto road = new RoadDto(roadCode,roadSeq,regcode,roadBonbun,roadBubun,roadBasement,roadName);

            houseList.add(house);
            boolean isVailable = service.isVailable(road.getRoad_code(),road.getRoad_Seq());
            if( isVailable ) {
                roadSet.add(road);
            }
//            service.addHouse();
        }
        System.out.println(roadSet);
        service.addRoad(roadSet);
        service.addHouse(houseList);
    }

    private static Element getElement(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document parser = builder.parse(url);
        Element element = parser.getDocumentElement();
        element.toString();
        return element;
    }

    private String getTextContent(Element el, String item) {
        try {
            return el.getElementsByTagName(item).item(0).getTextContent();
        } catch (NullPointerException e) {
            return "없음";
        }
    }
}
