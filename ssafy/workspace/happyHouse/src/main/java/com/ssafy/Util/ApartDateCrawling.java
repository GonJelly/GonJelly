package com.ssafy.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;

public class ApartDateCrawling {

	private static final String ServiceKey = "T35MiOIjU6pXC8h6yLXF2JSVHmSu7AiRQV34MshtSdkFr%2FB%2BoBw96PL3Pqw1SC24N260ppUGLyJdkRzXhbuBvA%3D%3D";
    private static final String PAGENO = "1";
    private static final String NUMBEROFRAW = "1000";
    private static String DEAL_YMD = "";
    private static final String FILEPATH = "C:" + File.separator + "aptFile" + File.separator + "info";
    
    public void loading() {
    	String msg = "아파트 데이터 크롤링 중";
    	try {
    		// 날짜 확인
    		Date date = new Date();
    		SimpleDateFormat simple = new SimpleDateFormat("yyyyMM");
    		// 현재 날짜 년,월 가져오기
    		int current_year = Integer.parseInt(simple.format(date).substring(0,4));
            int current_month = Integer.parseInt(simple.format(date).substring(4,6));
    		
            // 파일 불러오기
            File file = null;
            List<String> gugunList = getRegcodes();
            for (int year = current_year; year > current_year-10; year--) {
            	// 해에 따라서 분기
                if( current_year != year ) {
                	// 같은 해가 아니라면 12월 전부 데이터 가져오기
                    for( int m = 1; m <= 12; m++) {
                        String month = m < 10 ? "0" + m : String.valueOf(m);
                        for( String LAWD_CD : gugunList) {
                        	String path = FILEPATH + File.separator + LAWD_CD + File.separator + year + month;
                        	if( !Files.isDirectory(Paths.get(path))) {
                        		Files.createDirectories(Paths.get(path));
                        		System.out.println("create new directroy");
                        	}
                        	String fileName = LAWD_CD + "_" + year + month + "_" + PAGENO + ".xml";
                        	file = new File( path + File.separator + fileName);
                        	// 존재한다면 다음 저장하지 않음
                        	if( file.exists() ) continue;
                        	
                        	DEAL_YMD = (year) + month;
//                        	System.out.println(LAWD_CD + "::" + DEAL_YMD);
                        	Document document = getApartInfo(DEAL_YMD,LAWD_CD);
//                        	System.out.println(document);
                        	
                        	saveFile(document,file);
//                        	System.out.println("save OK");
                        }
                    }
                } else {
                	// 같은 해라면 현재월까지 데이터 가져오기
                    for( int m = 1; m <= current_month; m++) {
                        String month = m < 10 ? "0" + m : String.valueOf(m);
	                    for( String LAWD_CD : gugunList) {
	                    	String path = FILEPATH + File.separator + LAWD_CD + File.separator + year + month;
                        	if( !Files.isDirectory(Paths.get(path))) {
                        		Files.createDirectories(Paths.get(path));
                        		System.out.println("create new directroy");
                        	}
                        	String fileName = LAWD_CD + "_" + year + month + "_" + PAGENO + ".xml"; 
                        	file = new File( path + File.separator + fileName);
	                    	
                        	// 존재한다면 다음 저장하지 않음
	                    	if( file.exists() ) continue;
	                    	
	                    	DEAL_YMD = (year) + month;
//	                    	System.out.println(LAWD_CD + "::" + DEAL_YMD);
	                    	Document document = getApartInfo(DEAL_YMD,LAWD_CD);
//	                    	System.out.println(document);
	                    	
	                    	saveFile(document,file);
//	                    	System.out.println("save OK");
	                    }
                    }
                }
            }
            
    	} catch ( ParserConfigurationException e ) {
    		e.printStackTrace();
    	} catch ( IOException e ) {
    		e.printStackTrace();
    	} catch ( Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private List<String> getRegcodes() {
		Set<String> gugunCode = new HashSet<String>();
		List<String> gugunList = new ArrayList<String>();
    	try {
    		// 시코드!!
            String[] sidoCode = {"42","41","48","47","29","27","30","26","11","36","31","28","46","45","50","44","43"};
            
            for(String code : sidoCode) {
                String pattern = code + "*";
                JSONObject jsons = getJson("https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes",pattern);
                if ( jsons.isEmpty() ) continue;
                
                JSONArray regcodes = jsons.getJSONArray("regcodes");
                
                for(int i = 0; i < regcodes.length(); i++) {
                    JSONObject dong = regcodes.getJSONObject(i);
                    String sigunguCode = dong.getString("code").substring(0,5);
//                    String eubmyundongCode = dong.getString("code").substring(5).trim();
                    gugunCode.add(sigunguCode);
                }
            }
            Iterator<String> iter = gugunCode.iterator();
            while(iter.hasNext()) {
            	String code = iter.next();
            	gugunList.add(code);
            }
            Collections.sort(gugunList);
            return gugunList;
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
    }
    
    private static JSONObject getJson(String uri,String pattern) throws IOException {
        // "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes"
        StringBuilder city = new StringBuilder(uri);
        city.append("?regcode_pattern=");
        city.append(pattern);
        city.append("&is_ignore_zero=true");

        URL path = new URL(city.toString());
        InputStream in = path.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        String jsonText = jsonReadAll(br);
        System.out.println(jsonText);
        JSONObject json = new JSONObject(jsonText);
        return json;
    }
    
    private static String jsonReadAll(Reader reader) throws IOException{
        StringBuilder sb = new StringBuilder();

        int cp;
        while((cp = reader.read()) != -1){
            sb.append((char) cp);
        }

        return sb.toString();
    }
    
    private static Document getApartInfo(String _DEAL_YMD, String LAWD_CD) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
        url.append("?serviceKey=").append(ServiceKey);
        url.append("&pageNo=").append(PAGENO);
        url.append("&numOfRows=").append(NUMBEROFRAW);
        url.append("&LAWD_CD=").append(LAWD_CD);
        url.append("&DEAL_YMD=").append(_DEAL_YMD);
//        System.out.println(url.toString());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document parser = builder.parse(url.toString());
        
        return parser;
    }
    
    private static void saveFile(Document document,File file) throws IOException, TransformerException{
    	
    	FileOutputStream out = new FileOutputStream(file);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(out);
        
        transformer.transform(source, result);
    }
    
}
