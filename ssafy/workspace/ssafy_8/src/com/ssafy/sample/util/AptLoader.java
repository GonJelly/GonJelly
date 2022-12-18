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
    
    public String load(){

        String msg = null;

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
            msg = "데이터를 저장하는데 [ParserConfigurationException]가 발생하였습니다.";
            return msg;
        } catch (SAXException e) {
            msg = "데이터를 저장하는데 [SAXException]가 발생하였습니다.";
            return msg;
        } catch (IOException e) {
            msg = "데이터를 저장하는데 [IOException]가 발생하였습니다.";
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg = "데이터를 저장하는데 오류가 발생하였습니다.";
            return msg;
        }

        msg = "데이터를 정상적으로 저장하였습니다.";
        return msg;
    }

    private void saveAPT(String _DEAL_YMD, CityDto city) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
        url.append("?serviceKey=").append("T35MiOIjU6pXC8h6yLXF2JSVHmSu7AiRQV34MshtSdkFr%2FB%2BoBw96PL3Pqw1SC24N260ppUGLyJdkRzXhbuBvA%3D%3D");
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

            String code = getTextContent(el,"일련번호");
            String aptName = getTextContent(el,"아파트");
            String buildYear = getTextContent(el,"건축년도");
            String year = getTextContent(el,"년");
            String month = getTextContent(el,"월");
            String day = getTextContent(el,"일");
            String Deal_Amount = getTextContent(el,"거래금액").trim();
            String areaExUse = getTextContent(el,"전용면적");
            String floor = getTextContent(el,"층");
            String regcode = getTextContent(el,"법정동시군구코드");
            String eubmyudong = getTextContent(el,"법정동읍면동코드");
            String roadCode = getTextContent(el,"도로명코드");
            String bonbun = getTextContent(el,"법정동본번코드");
            String bubun = getTextContent(el,"법정동부번코드");
            String Jibun = getTextContent(el,"지번");

            HouseInfoDto house = new HouseInfoDto(code,aptName,buildYear,year,month,day
                    ,Deal_Amount,areaExUse,floor,regcode,eubmyudong,roadCode,bonbun,bubun,Jibun);

            String roadSeq = getTextContent(el,"도로명일련번호코드");
            String roadBonbun = getTextContent(el,"도로명건물본번호코드");
            String roadBubun = getTextContent(el,"도로명건물부번호코드");
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
