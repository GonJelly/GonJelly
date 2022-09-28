package com.ssafy.apt.model.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.StringValue;
import com.mysql.cj.xdevapi.JsonParser;
import com.ssafy.apt.model.dto.AptDto;
import com.ssafy.apt.model.dto.CityDto;
import com.ssafy.apt.model.dto.DongDto;
import com.ssafy.apt.model.service.AptService;
import com.ssafy.apt.model.service.AptServiceImpl;
import com.ssafy.sample.util.AptLoader;
import com.ssafy.sample.util.CityLoader;
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


@WebServlet(value = "/ControllerApt")
public class ControllerApt extends HttpServlet {

    private static AptService service = AptServiceImpl.getInstance();
    private static int mode = 0;

    @Override
    public void init() throws ServletException{

        CityLoader cityLoader = CityLoader.getInstance();
//        cityLoader.load();

        AptLoader aptLoader = AptLoader.getInstance();
//        aptLoader.load();
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
        String act = request.getParameter("action");
        String path = null;
        if( act.equals("modal")) {
            path = modal(request,response);
            forward(request,response,path);
        } else if( act.equals("mvsearch") ) {
            path = mvSearch(request,response);
            forward(request,response,path);
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
        List<AptDto> list = null;
//        List<AptDto> list = service.getApt(DEAL_YMD,regcode);

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

        return "apt/aptmodal.jsp";
    }

    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String DEAL_YMD = request.getParameter("DEAL_YMD");
        String regcode = request.getParameter("regcode");

        System.out.println(DEAL_YMD);
        System.out.println(regcode);
        List<AptDto> list = null;
//        List<AptDto> list = service.getApt(DEAL_YMD,regcode);

        JSONObject data = new JSONObject();
        data.put("apt",list);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.write(data.toString());
        out.flush();
        out.close();

    }

    protected String mvSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
//            Map<String,Object> cityList = service.getCityMap();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return "apt/aptSearch.jsp";
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dist = request.getRequestDispatcher(path);
        dist.forward(request,response);
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(path);
    }
}
