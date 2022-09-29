package com.ssafy.apt.model.dao;

import com.ssafy.apt.model.dto.*;
import com.ssafy.sample.util.DBUtil;

import java.sql.*;
import java.util.*;


public class AptDaoImpl implements  AptDao{
    private DBUtil db;

    private static AptDaoImpl instance = new AptDaoImpl();
    private AptDaoImpl() {
        db = DBUtil.getInstance();
    }

    @Override
    public List<HouseInfoDto> getApt(String _DEAL_YMD, String _regcode) {

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int idx = 0;
        List<HouseInfoDto> list = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from HouseInfo \n");
            sql.append("where DEAL_YMD = ? and regcode = ? \n");
            sql.append("order by Deal_Day desc \n");
            sql.append("limit 1");

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

                HouseInfoDto dto = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        } finally {
            db.close(rs,psmt,conn);
            return list;
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
        sql.append("from city \n");
        sql.append("order by Sigungu_code asc");

        List<CityDto> list = new ArrayList<>();
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql.toString());

        while ( rs.next() ) {
            CityDto city = new CityDto();
            city.setSigungu_code(rs.getString("Sigungu_code"));
            city.setSido(rs.getString("city_sido"));
            city.setGugun(rs.getString("city_gungu"));

            list.add(city);
        }

        db.close(rs,stmt,conn);
        return list;
    }

    @Override
    public int addDong(List<DongDto> list) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into dong ( Sigungu_code, Eubmyundong_code, Dong)\n");
        sql.append("values (?, ?, ?)");
        for( int x = 0; x < list.size() - 1; x++) {
            sql.append(", (?, ?, ?)");
        }

        int idx = 0;
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        for( DongDto dong : list) {
            pstmt.setString(++idx, dong.getSigungu_code());
            pstmt.setString(++idx, dong.getEubmyundong_code());
            pstmt.setString(++idx, dong.getDong());
        }
        int result = pstmt.executeUpdate();
        db.close(pstmt,conn);
        return result;
    }

    @Override
    public List<DongDto> getDong(String sigunguCode) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DongDto> list = null;
        try {
            list = new ArrayList<>();
            StringBuilder sql = new StringBuilder();
            sql.append("select Sigungu_code, Eubmyundong_code, Dong \n");
            sql.append("from dong \n");
            sql.append("where Sigungu_code = ? \n");
            sql.append("order by Eubmyundong_code asc");

            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, sigunguCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                DongDto dong = new DongDto();
                dong.setSigungu_code(rs.getString("Sigungu_code"));
                dong.setEubmyundong_code(rs.getString("Eubmyundong_code"));
                dong.setDong(rs.getString("Dong"));

                list.add(dong);

            }
        } finally {
            db.close(rs,pstmt,conn);
        }

        return list;
    }

    @Override
    public int addHouse(List<HouseInfoDto> list) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into houseInfo ( house_Code\n" +
                ",house_Name\n" +
                ",build_Year\n" +
                ",Deal_Year\n" +
                ",Deal_Month\n" +
                ",Deal_Day\n" +
                ",Deal_Amount\n" +
                ",Use_Area\n" +
                ",Floor\n" +
                ",Sigungu_code\n" +
                ",Eubmyundong_code\n" +
                ",Road_code\n" +
                ",Bonbun\n" +
                ",Bubun\n" +
                ",Jibun)\n");
        sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        for( int x = 0; x < list.size() - 1; x++) {
            sql.append(", (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        }

        int idx = 0;
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        for(HouseInfoDto house : list) {

            pstmt.setString(++idx, house.getHouse_Code());
            pstmt.setString(++idx, house.getHouse_Name());
            pstmt.setString(++idx, house.getBuild_Year());
            pstmt.setString(++idx, house.getDeal_Year());
            pstmt.setString(++idx, house.getDeal_Month());
            pstmt.setString(++idx, house.getDeal_Day());
            pstmt.setString(++idx, house.getDeal_Amount());
            pstmt.setString(++idx, house.getUse_Area());
            pstmt.setString(++idx, house.getFloor());
            pstmt.setString(++idx, house.getSigungu_code());
            pstmt.setString(++idx, house.getEubmyundong_code());
            pstmt.setString(++idx, house.getRoad_code());
            pstmt.setString(++idx, house.getBonbun());
            pstmt.setString(++idx, house.getBubun());
            pstmt.setString(++idx, house.getJibun());

        }

        int result = pstmt.executeUpdate();
        db.close(pstmt,conn);
        return result;

    }

    @Override
    public int addRoad(Set<RoadDto> set) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into road(Road_code\n" +
                ",Road_Seq\n" +
                ",Sigungu_Code\n" +
                ",Road_Bonbun\n" +
                ",Road_Bubun\n" +
                ",Road_Basement_Code\n" +
                ",Road_Name)\n");
        sql.append("values (?, ?, ?, ?, ?, ?, ?)");
        for( int x = 0; x < set.size() - 1; x++) {
            sql.append(", (?, ?, ?, ?, ?, ?, ?)");
        }

        int idx = 0;
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        Iterator<RoadDto> iterator = set.iterator();

        while ( iterator.hasNext() ) {
            RoadDto road = iterator.next();
            System.out.println(road);
            pstmt.setString(++idx, road.getRoad_code());
            pstmt.setString(++idx, road.getRoad_Seq());
            pstmt.setString(++idx, road.getSigungu_Code());
            pstmt.setString(++idx, road.getRoad_Bonbun());
            pstmt.setString(++idx, road.getRoad_Bubun());
            pstmt.setString(++idx, road.getRoad_Basement_Code());
            pstmt.setString(++idx, road.getRoad_Name());
        }
        int result = pstmt.executeUpdate();
        db.close(pstmt,conn);
        return result;
    }

    @Override
    public RoadDto getRoad(String Road_code, String Road_Seq) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("select * \n");
        sql.append("from road \n");
        sql.append("where Road_code = ? and \n");
        sql.append("      Road_Seq = ?");

        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1,Road_code);
        pstmt.setString(2,Road_Seq);

        ResultSet rs = pstmt.executeQuery();
        while ( rs.next() ) {
            RoadDto road = new RoadDto();

            road.setRoad_code(rs.getString("Road_code"));
            road.setRoad_Seq(rs.getString("Road_Seq"));
            road.setRoad_Name(rs.getString("Road_Name"));

            db.close(rs,pstmt,conn);
            return road;
        }

        db.close(rs,pstmt,conn);
        return null;
    }

    @Override
    public Map<String, Integer> getCity() throws SQLException {

        Map<String,Integer> map  = new HashMap<>();;
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct substring(Sigungu_Code,1,2) as sidoCode \n");
        sql.append("        , substring_index(city_sido,' ',1) as sidoName \n");
        sql.append("from city");

        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql.toString());

        while ( rs.next() ) {
            map.put(rs.getString("sidoName"),rs.getInt("sidoCode"));
        }

        db.close(rs,stmt,conn);
        return map;
    }

    public static AptDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Map<String, String> getGungu(String sidoCode) throws SQLException {

        Map<String, String> map = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder();
            sql.append("select Sigungu_code as sigunguCode, city_gungu as gungu \n");
            sql.append("from city \n");
            sql.append("where Sigungu_code like ?");

            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql.toString());

            pstmt.setString(1,sidoCode + "%");

            rs = pstmt.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("gungu"), rs.getString("sigunguCode"));
            }
        } finally {
            db.close(rs,pstmt,conn);
        }
        return map;
    }
}
