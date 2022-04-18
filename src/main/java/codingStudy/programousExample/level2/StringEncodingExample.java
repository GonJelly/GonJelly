package codingStudy.programousExample.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringEncodingExample {


    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        BufferedReader si = new BufferedReader(new InputStreamReader(System.in));
        String s = si.readLine();

        System.out.println(solution.solution(s));
    }


}

class Solution {

    public int solution(String s){

        int answer = 0;

        List<StringBuffer> strEncodingCase = new ArrayList<StringBuffer>();

        int cs = 0;


        for(int i = 1; i <= s.length(); i++) {

            Map<String, Integer> str_encoding = new HashMap<String, Integer>();
            List<String> temp = new ArrayList<String>();
            StringBuffer temp_end = new StringBuffer();

            // 순서
            int idx = 0;

            // 문자열 자르는 부분
            for(int j = 0; j < s.length(); j = j + i) {

                if( s.length() < j + i) {
                    temp.add(idx,s.substring(j));
                    break;
                }

                temp.add(idx,s.substring(j,j+i));
                idx++;

            }

            int count = 1;

            // 반복되는 문자 갯수체크
            for(int x = 0; x < temp.size(); x++) {
                if( x == 0 ) {
                    str_encoding.put(temp.get(x),count);
                } else {
                    if(temp.get(x).equals(temp.get(x-1))) {
                        count++;
                        str_encoding.put(temp.get(x-1),count);
                    } else {
                        count = 1;
                        str_encoding.put(temp.get(x),count);

                        if( str_encoding.get(temp.get(x-1)) == 1) {
                            temp_end.append(temp.get(x-1));
                        } else {
                            temp_end.append(str_encoding.get(temp.get(x-1)) + temp.get(x-1));
                        }
                    }
                }
            }

            strEncodingCase.add(cs,temp_end);
            cs++;

        }

        for(int i = 0; i < strEncodingCase.size()-1; i++) {
            StringBuffer temp = strEncodingCase.get(i);

            for(int j = 1; j < strEncodingCase.size(); j++){
                StringBuffer comTemp = strEncodingCase.get(j);

                if( temp.length() < comTemp.length() ) {
                    answer = temp.length();

                } else {
                    answer = comTemp.length();
                }
            }
        }

        return answer;
    }

}
