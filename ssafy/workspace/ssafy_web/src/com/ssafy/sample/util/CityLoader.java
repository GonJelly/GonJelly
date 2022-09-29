package com.ssafy.sample.util;

import com.ssafy.apt.model.dto.CityDto;
import com.ssafy.apt.model.dto.DongDto;
import com.ssafy.apt.model.service.AptService;
import com.ssafy.apt.model.service.AptServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CityLoader {

    private static CityLoader instance = new CityLoader();
    private AptService service;
    private CityLoader() { service = AptServiceImpl.getInstance(); }
    public static CityLoader getInstance() { return instance; }

    public void dong() {

        try {
            List<CityDto> list = service.getCityList();
            for(CityDto city : list) {
                String pattern = city.getSigungu_code() + "*";
                JSONObject jsons = getJson("https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes",pattern);
                if ( jsons.isEmpty() ) continue;

                List<DongDto> dongList = new ArrayList<>();
                JSONArray regcodes = jsons.getJSONArray("regcodes");

                for(int i = 0; i < regcodes.length(); i++) {
                    DongDto store = new DongDto();
                    JSONObject dong = regcodes.getJSONObject(i);
                    String sigunguCode = dong.getString("code").substring(0,5);
                    String eubmyundongCode = dong.getString("code").substring(5).trim();
                    String[] name = dong.getString("name").split(" ");
                    String dongNm = name[name.length - 1];

                    store.setSigungu_code(sigunguCode);
                    store.setEubmyundong_code(eubmyundongCode);
                    store.setDong(dongNm);

                    dongList.add(store);
                }

                service.addDong(dongList);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            JSONObject jsons = getJson("https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes", "*00000000");
            JSONArray regcodes = jsons.getJSONArray("regcodes");

            for(int i = 0; i < regcodes.length(); i++) {
                JSONObject json = regcodes.getJSONObject(i);

                String sido = json.getString("code");
                String pattern = sido.substring(0,2) + "*00000";
                JSONObject gugun = getJson("https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes",pattern);
                JSONArray reg = gugun.getJSONArray("regcodes");

                for( int j = 0; j < reg.length(); j++) {
                    System.out.println(reg.getJSONObject(j));
                    String LAWD_CD = reg.getJSONObject(j).getString("code").substring(0, 5);
                    String[] name = reg.getJSONObject(j).getString("name").split(" ");

                    int idx = name.length > 2 ? 2 : 1;
                    CityDto city = new CityDto();
                    city.setSigungu_code(LAWD_CD);

                    // 군,구 로 끝나면
                    if( name[idx].charAt(name[idx].length() - 1) == '구' || name[idx].charAt(name[idx].length() - 1) == '군') {
                        StringBuilder input = new StringBuilder();
                        for(int z = 0; z < idx; z++) {
                            input.append(name[z] + " ");
                        }
                        city.setSido(input.toString());
                        city.setGugun(name[idx]);
                    } else {
                        StringBuilder input = new StringBuilder();
                        for(int z = 0; z <= idx; z++) {
                            input.append(name[z] + " ");
                        }
                        city.setSido(input.toString());
                        city.setGugun("");
                    }

                    service.addCity(city);
                }
            }
            // 10초
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            dong();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("도시 저장 중 오류 발생");
        }
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
}
