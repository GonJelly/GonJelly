package com.ssafy.apt.model.service;


import com.ssafy.apt.model.dto.CityDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ssafy.apt.model.dao.AptDao;
import com.ssafy.apt.model.dao.AptDaoImpl;
import com.ssafy.apt.model.dto.AptDto;

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
    public static final String fileNm = "aptDocument.txt";
    private FileOutputStream out;
    private static AptServiceImpl instance = new AptServiceImpl();

    private AptServiceImpl() {
        dao = AptDaoImpl.getInstance();
    }

    public static AptServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<AptDto> getApt(String DEAL_YMD, String regcode) {
        return dao.getApt(DEAL_YMD, regcode);
    }

    public void saveApt(int pageNo, int numOfRows, String _LAWD_CD, String _DEAL_YMD){
        try {
            StringBuilder url = new StringBuilder();
            url.append("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
            url.append("?serviceKey=").append("BXsbI8mohhDdAvC7vE9mwtVm9rlr%2FAjpUw57Fr3shbk97SQG%2FULBIZpeRLx4O%2Fi4mwLqfvGiycnIxubRO%2Fus%2Bg%3D%3D");
            url.append("&pageNo=").append(pageNo);
            url.append("&numOfRows=").append(numOfRows);
            url.append("&LAWD_CD=").append(_LAWD_CD);
            url.append("&DEAL_YMD=").append(_DEAL_YMD);

            Element element = getElement(url.toString());
            fileUpdate(element, _DEAL_YMD);    // 파일저장
            NodeList apts = element.getElementsByTagName("item");
            System.out.println(apts.getLength());
            Node current = null;

            for( int i = 0; i < apts.getLength(); i++) {
                current = apts.item(i);
                Element el = (Element) current;
                String Code = el.getElementsByTagName("일련번호").item(0).getTextContent();
                String LAWD_CD = el.getElementsByTagName("지역코드").item(0).getTextContent();

                String year = el.getElementsByTagName("년").item(0).getTextContent();
                String month = el.getElementsByTagName("월").item(0).getTextContent();

                String DEAL_YMD = year + (month.length() > 1 ? month : "0"+month );
                String Deal_Amount = el.getElementsByTagName("거래금액").item(0).getTextContent();
                String regcode = el.getElementsByTagName("법정동시군구코드").item(0).getTextContent();
                String Eubmyudong = el.getElementsByTagName("법정동읍면동코드").item(0).getTextContent();
                String Dong = el.getElementsByTagName("법정동").item(0).getTextContent().trim();
                String AptName = el.getElementsByTagName("아파트").item(0).getTextContent();
                String AreaExUse = el.getElementsByTagName("전용면적").item(0).getTextContent();
                String Jibun = el.getElementsByTagName("지번").item(0).getTextContent();
                String Floor = el.getElementsByTagName("층").item(0).getTextContent();
                String Build_Year = el.getElementsByTagName("건축년도").item(0).getTextContent();
                String Read_Name = el.getElementsByTagName("도로명").item(0).getTextContent();
                String Bonbun = el.getElementsByTagName("도로명건물본번호코드").item(0).getTextContent();
                String Bubun = el.getElementsByTagName("도로명건물부번호코드").item(0).getTextContent();

                AptDto dto = new AptDto(Code,LAWD_CD,DEAL_YMD,Deal_Amount,regcode,Eubmyudong,Dong,
                        AptName, AreaExUse,Jibun,Floor,Build_Year,Read_Name,Bonbun,Bubun);

                System.out.println(dto);
                dao.saveApt(dto);
                Thread.sleep(1000);
            }



        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Element getElement(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document parser = builder.parse(url);
        Element element = parser.getDocumentElement();
        element.toString();
        return element;
    }

    private void fileUpdate(Element el, String date) {
        try {
            File path = new File(date+fileNm);
            FileOutputStream out = new FileOutputStream(path);
            out.write(el.toString().getBytes());
            out.flush();
            out.close();
        } catch(IOException e) {
            System.out.println("파일출력 실패");
            e.printStackTrace();
        }

    }

    @Override
    public int addCity(CityDto city) throws Exception {
        return dao.addCity(city);
    }

    @Override
    public List<CityDto> getCityList() throws Exception {
        return dao.getCityList();
    }
}
