import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;

class Solution {
    public int solution(String[][] book_time) {
        // 방의 갯수
        int answer = 0;
        // 방을 시작시간 기준으로 정령
        Arrays.sort(book_time,new Comparator<String[]>() {
            public int compare(String[] o1,String[] o2) {
                int[] time1 = splitTime(o1[0]);
                int[] time2 = splitTime(o2[0]);
                
                // 분으로 비교
                if( time1[0] == time2[0] ) {    
                    return time1[1] - time2[1];
                } else {
                    return time1[0] - time2[0];
                }
            }
        });
        
        // end time을 저장할 변수 ( Queue )
        PriorityQueue<String> checkout = new PriorityQueue(new Comparator<String>() {
            public int compare(String o1,String o2) {
                int[] time1 = splitTime(o1);
                int[] time2 = splitTime(o2);
                
                // 분으로 비교
                if( time1[0] == time2[0] ) {    
                    return time1[1] - time2[1];
                } else {
                    return time1[0] - time2[0];
                }
            }
        });
        
        // 방의 갯수만큼 비교를 한다.
        for(String[] room : book_time) {
            // 방이 없으면
            if( checkout.size() == 0 ) {
                // 방 1개 추가
                answer++;
                checkout.offer(room[1]);
            } else {
                // 해당 방의 시작시간과 현재있는 방의 체크아웃 시간을 비교
                int[] current = splitTime(room[0]);
                // 제일 빠르게 나오는 방의 end time
                String temp = checkout.poll();
                int[] out = splitTime(temp);
                // 10분을 추가
                out[1] += 10;
                if( out[1] >= 60 ) {
                    out[0] += 1;
                    out[1] -= 60;
                }
                // 체크아웃을 하지 않았다면 새로운 방을 추가
                if( current[0] < out[0] ) {
                    answer++;
                    // 현재 방의 체크아웃 시간을 추가
                    checkout.offer(temp);
                    checkout.offer(room[1]);
                } else if( current[0] > out[0] ) { // 체크아웃 상태라면 체크 아웃한 방에 들어간다.
                    checkout.offer(room[1]);
                } else { // 시간이 같다면 분으로 비교
                    if( current[1] < out[1] ) {
                        answer++;
                        checkout.offer(temp);
                        checkout.offer(room[1]);
                    } else {
                        checkout.offer(room[1]);
                    }
                }
            }
        }
        
        // 손님의 수를 변수에 저장
        return answer;
    }
    
    // 00:00 문자열을 시간 분으로 split하는 메소드
    public int[] splitTime(String o1) {
        int[] result = new int[2];
        String[] temp = o1.split(":");
        for(int i = 0; i < 2; i++) {
            result[i] = Integer.parseInt(temp[i]);
        }
        return result;
    }
}