class Solution {
    
    private static int lux, luy, rdx, rdy;
    
    public int[] solution(String[] wallpaper) {
        
        lux = luy = 50;
        rdx = rdy = 0;
        
        for(int r = 0, sr = wallpaper.length; r < sr; r++ ) {
            for( int c = 0, sc = wallpaper[r].length(); c < sc; c++ ) {
                
                if( wallpaper[r].charAt(c) == '#') {
                    lux = Math.min(lux, r);
                    rdx = Math.max(rdx, r);
                    luy = Math.min(luy, c);
                    rdy = Math.max(rdy, c);
                }
                
            }
        }
    
        return new int[] {lux, luy, rdx + 1, rdy + 1};
    }
}