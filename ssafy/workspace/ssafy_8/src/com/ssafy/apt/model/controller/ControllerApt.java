package com.ssafy.apt.model.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.StringValue;
import com.mysql.cj.xdevapi.JsonParser;
import com.ssafy.apt.model.dto.AptDto;
import com.ssafy.apt.model.service.AptService;
import com.ssafy.apt.model.service.AptServiceImpl;
import com.sun.org.apache.xerces.internal.parsers.XMLDocumentParser;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@WebServlet(name = "ControllerApt", value = "/ControllerApt")
public class ControllerApt extends HttpServlet {

    private static AptService service = AptServiceImpl.getInstance();
    private static int mode = 0;

    @Override
    public void init() throws ServletException {
        if( mode == 0) {
            System.out.println("아파트 조회");
            try {
                JSONObject jsons = getJson("*00000000");
                JSONArray regcodes = jsons.getJSONArray("regcodes");

                Date date = new Date();
                SimpleDateFormat simple = new SimpleDateFormat("yyyyMM");
//                System.out.println(simple.format(date));
                int current_year = Integer.parseInt(simple.format(date).substring(0,4));
                int current_month = Integer.parseInt(simple.format(date).substring(4,6));
                System.out.println(current_month);
//                System.out.println(regcodes.length());
                int pageNo = 1;
                int numOfRows = 1000;
                String LAWD_CD = "";
                String DEAL_YMD = "";

                for(int i = 0; i < regcodes.length(); i++) {
                    JSONObject json = regcodes.getJSONObject(i);
                    String sido = json.getString("code");
                    System.out.println(sido);
                    JSONObject gugun = getJson(sido.substring(0,2) + "*00000");
                    JSONArray reg = gugun.getJSONArray("regcodes");
                    System.out.println(reg.length());
                    for( int j = 0; j < reg.length(); j++) {
                        LAWD_CD = reg.getJSONObject(j).getString("code").substring(0, 5);
//                        System.out.println(LAWD_CD);
                        for (int z = current_year; z > current_year-10; z--) {
                            if( current_year != z ) {
                                for( int m = 1; m <= 12; m++) {
                                    String month = m < 10 ? "0" + m : String.valueOf(m);
                                    DEAL_YMD = (z) + month;
                                    System.out.println(DEAL_YMD);
                                    service.saveApt(pageNo,numOfRows,LAWD_CD,DEAL_YMD);
                                }
                            } else {
                                for( int m = 1; m <= current_month; m++) {
                                    String month = m < 10 ? "0" + m : String.valueOf(m);
                                    System.out.println(DEAL_YMD);
                                    DEAL_YMD = (z) + month;
                                    service.saveApt(pageNo,numOfRows,LAWD_CD,DEAL_YMD);
                                }
                            }
                        }
                        // 임시
                        break;
                    }
                    System.out.println("in");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new XMLDocumentParser();

            mode = 1;
        }
    }

    private static JSONObject getJson(String pattern) throws IOException {
        StringBuilder city = new StringBuilder("https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes");
        city.append("?regcode_pattern=");
        city.append(pattern);
        city.append("&is_ignore_zero=true");
        URL path = new URL(city.toString());
        InputStream in = path.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        String jsonText = jsonReadAll(br);
        JSONObject json = new JSONObject(jsonText);
        return json;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandler(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doHandler(request,response);
    }

    protected void doHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        String path = null;
        if( act.equals("modal")) {
            path = modal(request,response);
            forward(request,response,path);
        } else if( act.equals("mvsearch") ) {
            path = "jsp/apt/aptSearch.jsp";
            redirect(request,response,path);
        } else if( act.equals("search") ) {
            search(request,response);
        } else if ( act.equals("Detail") ) {
//            searchAll(request,response);
        }
    }

    protected String modal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // mode=2&regcode=\${regcode}&date=\${date}&address=\${address}&floor=\${floor}
        String DEAL_YMD = request.getParameter("date");
        String regcode = request.getParameter("regcode");
        String address = request.getParameter("address");
        String floor = request.getParameter("floor");

        System.out.println("주소 : " + address);
        System.out.println("지역코드 : " + regcode);
        System.out.println("계약년월 : " + DEAL_YMD);
        System.out.println("층수 : " + floor);
        List<AptDto> list = service.getApt(DEAL_YMD,regcode);

        String[] temp = address.split(" ");
        String dong = (temp.length > 3 ? temp[2] : temp[1]);
        String jibun = (temp.length > 3 ? temp[3] : temp[2]);

        for(AptDto dto : list) {
            if( dto.getDong().equals(dong)
                    && dto.getFloor().equals(floor)
                        && dto.getJibun().equals(jibun)) {
                System.out.println(dto);
                request.setAttribute("apt",dto);
            }
        }

        request.setAttribute("address",address);

        return "jsp/apt/aptmodal.jsp";
    }

    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String DEAL_YMD = request.getParameter("DEAL_YMD");
        String regcode = request.getParameter("regcode");

        System.out.println(DEAL_YMD);
        System.out.println(regcode);

        List<AptDto> list = service.getApt(DEAL_YMD,regcode);

        JSONObject data = new JSONObject();
        data.put("apt",list);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.write(data.toString());
        out.flush();
        out.close();

    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dist = request.getRequestDispatcher(path);
        dist.forward(request,response);
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(path);
    }
    private static String jsonReadAll(Reader reader) throws IOException{
        StringBuilder sb = new StringBuilder();

        int cp;
        while((cp = reader.read()) != -1){
            sb.append((char) cp);
        }

        return sb.toString();
    }
}
