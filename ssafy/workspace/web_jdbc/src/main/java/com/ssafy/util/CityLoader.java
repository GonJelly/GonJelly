package com.ssafy.util;

import com.ssafy.main.model.CityDto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class CityLoader {

    private static CityLoader instance = new CityLoader();
    private CityLoader() {}
    public static CityLoader getInstance() { return instance; }

    public void load() {
        try {
            JSONObject jsons = getJson("https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes", "*00000000");
            JSONArray regcodes = jsons.getJSONArray("regcodes");

            for(int i = 0; i < regcodes.length(); i++) {
                JSONObject json = regcodes.getJSONObject(i);
                System.out.println(json);
                String sido = json.getString("code");
                String pattern = sido.substring(0,2) + "*00000";
                JSONObject gugun = getJson("https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes",pattern);
                JSONArray reg = gugun.getJSONArray("regcodes");

                for( int j = 0; j < reg.length(); j++) {
//                    System.out.println(reg.getJSONObject(j));
                    String LAWD_CD = reg.getJSONObject(j).getString("code").substring(0, 5);
                    String[] name = reg.getJSONObject(j).getString("name").split(" ");

                    CityDto city = new CityDto();
                    city.setSigungu_code(LAWD_CD);
                    city.setSido(name[0]);
                    city.setGugun(name[1]);

                    System.out.println(city);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
