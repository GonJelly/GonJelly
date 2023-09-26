class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        boolean stop = false;
        // 1. 배달을 갈 제일 먼 집을 구한다.
        // 2. 수거를 해야하는 먼 집을 구한다.
        int del = n - 1;
        int pic = n - 1;
        
        do {
            del = getDelivery(del, deliveries);
            pic = getDelivery(pic, pickups);
            // 5. 제일 멀리 간 거리를 황복으로 구한다.
            //    왕복을 하면 cap만큼 배달과 수거가 이루어진다.
            answer += Math.max(del + 1,pic + 1) * 2;
            // 3. 먼저 배달을 한다.
            for(int i = del, box = cap; i >= 0; i--) {
                int home = deliveries[i];
                deliveries[i] = home >= box ? home - box : 0;
                box = box >= home ? box - home : 0;
                if(deliveries[i] == 0) del--;
                // 상자를 전부 배달하면 종료
                if(box == 0) break;
            }
            for(int j = pic, box = 0; j >= 0; j--) {
                int home = pickups[j];
                int gap = cap - box;
                pickups[j] = home >= gap ? home - gap : 0;
                box += home > gap ? gap : home;
                if(pickups[j] == 0) pic--;
                if(box == cap) break;
            }
            // 6. 배달 or 수거해야하는 상자가 없다면 종료한다.
            if( del < 0 && pic < 0 ) stop = true;
        } while(!stop);
        // 1 ~ 5번을 반복한다.
        return answer;
    }
    
    private static int getDelivery(int far, int[] something) {
        for(int s = far; s >= 0; s--) {
            if(something[s] != 0) {
                return s;
            }
        }
        return -1;
    }
}