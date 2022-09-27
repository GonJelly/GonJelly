package com.ssafy.apt.model.dao;

import com.ssafy.apt.model.dto.AptDto;
import com.ssafy.apt.model.dto.CityDto;
import com.ssafy.sample.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AptDaoImpl implements  AptDao{
    private DBUtil db;

    private static AptDaoImpl instance = new AptDaoImpl();
    private AptDaoImpl() {
        db = DBUtil.getInstance();
    }

    @Override
    public List<AptDto> getApt(String _DEAL_YMD, String _regcode) {

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int idx = 0;
        List<AptDto> list = new ArrayList<>();

        try {


            StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from apt \n");
            sql.append("where DEAL_YMD = ? and regcode = ?");

            conn = db.getConnection();
            psmt = conn.prepareStatement(sql.toString());
            psmt.setString(++idx,_DEAL_YMD);
            psmt.setString(++idx,_regcode);
            rs = psmt.executeQuery();

            while( rs.next() ) {

                String code = rs.getString("Code");
                String LAWD_CD = rs.getString("LAWD_CD");
                String DEAL_YMD = rs.getString("DEAL_YMD");
                String Deal_Amount = rs.getString("Deal_Amount").trim();
                String regcode = rs.getString("regcode");
                String Eubmyudong = rs.getString("regcode");
                String Dong = rs.getString("Dong");
                String AptName = rs.getString("AptName");
                String AreaExUse = rs.getString("AreaExUse");
                String Jibun = rs.getString("Jibun");
                String Floor = rs.getString("Floor");
                String Build_Year = rs.getString("Build_Year");
                String Read_Name = rs.getString("Read_Name");
                String Bonbun = rs.getString("Bonbun");
                String Bubun = rs.getString("Jibun");

                AptDto dto = new AptDto(code,LAWD_CD,DEAL_YMD,Deal_Amount,regcode,Eubmyudong,Dong,AptName,AreaExUse,Jibun,Floor,Build_Year,Read_Name,Bonbun,Bubun);
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        } finally {
            db.close(rs,psmt,conn);
            return list;
        }
    }

    @Override
    public int saveApt(AptDto aptDto) {

        Connection conn = null;
        PreparedStatement psmt = null;
        int result = 0;

        try {

            StringBuilder sql = new StringBuilder();
            sql.append("insert into apt(code, lawd_cd, deal_ymd, deal_amount, regcode, eubmyudong, dong, aptName, areaExUse, jibun, floor, build_Year, read_Name, bonbun, bubun) \n");
            sql.append("value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            int idx = 0;
            conn = db.getConnection();
            psmt = conn.prepareStatement(sql.toString());

            psmt.setString(++idx,aptDto.getCode());
            psmt.setString(++idx,aptDto.getLAWD_CD());
            psmt.setString(++idx,aptDto.getDEAL_YMD());
            psmt.setString(++idx,aptDto.getDeal_Amount());
            psmt.setString(++idx,aptDto.getRegcode());
            psmt.setString(++idx,aptDto.getEubmyudong());
            psmt.setString(++idx,aptDto.getDong());
            psmt.setString(++idx,aptDto.getAptName());
            psmt.setString(++idx,aptDto.getAreaExUse());
            psmt.setString(++idx,aptDto.getJibun());
            psmt.setString(++idx,aptDto.getFloor());
            psmt.setString(++idx,aptDto.getBuild_Year());
            psmt.setString(++idx,aptDto.getRead_Name());
            psmt.setString(++idx,aptDto.getBonbun());
            psmt.setString(++idx,aptDto.getBubun());

            result = psmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(psmt,conn);
            return result;
        }
    }

    @Override
    public int addCity(CityDto city) throws SQLException{

        StringBuilder sql = new StringBuilder();
        sql.append("insert into city (Sigungu_code, city_sido, city_gungu )\n");
        sql.append("value (?, ?, ?)");

        int idx = 0;
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(++idx,city.getSigungu_code());
        pstmt.setString(++idx,city.getSido());
        pstmt.setString(++idx,city.getGugun());

        int result = pstmt.executeUpdate();
        db.close(pstmt,conn);
        return result;
    }

    @Override
    public List<CityDto> getCityList() throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("select Sigungu_code, city_sido, city_gungu \n");
        sql.append("from city");

        List<CityDto> list = new ArrayList<>();
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();

        while ( rs.next() ) {
            CityDto city = new CityDto();
            city.setSigungu_code(rs.getString("Sigungu_code"));
            city.setSido(rs.getString("city_sido"));
            city.setGugun(rs.getString("city_gungu"));

            list.add(city);
        }

        db.close(rs,pstmt,conn);
        return list;
    }

    public static AptDaoImpl getInstance() {
        return instance;
    }

}
