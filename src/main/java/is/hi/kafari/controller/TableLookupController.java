package is.hi.kafari.controller;

import is.hi.kafari.services.OTUService;
import static is.hi.kafari.services.TableLookupSevice.lookup;
import java.util.ArrayList;

/**
 *
 * @author Einar
 */
public class TableLookupController {
    
    
    /**
     * skilar bókstaf viðeigandi dýfu
     * @param depth dýpt dýfu
     * @param time tími dýfu
     * @return strengur sem inniheldur nýja bókstafinn
     */
    public static String getLetter(int depth, int time) {
        ArrayList<String> tableRow = lookup(depth, time, null);
        if (tableRow == null) return null;
        String letter = tableRow.get(0); // read letter from list
        return letter;
    }
    
    public static String getOTU(int depth,int diveTime){
        return OTUService.getOTU(depth, diveTime);
    }
    
    public static int getOTUDailyLimit(){
        return OTUService.getDailyLimit();
    }
    
    public static int getOTUReducedLungCapacity(){
        return OTUService.getReducedLungCapacity();
    }
    
    public static int getOTUDailyMax(){
        return OTUService.getDailyMax();
    }
    
    /**
     * býr til streng með upplýsingum um decompression viðeigandi dýfu
     * @param depth dýpt dýfu
     * @param time tími dýfu
     * @return strengur sem inniheldur tíma sem þarf að stoppa á vieigandi dýpi
     */
    public static String getDecompressionString(int depth, int time) {
        ArrayList<String> tableRow = lookup(depth, time, null);
        if (tableRow == null) return null;
        String[] depths = new String[]{"", "3m", "6m", "12m", "15m"};
        String decompression = "";
        for(int i = 1; i < tableRow.size(); i++) {
            // build up decompression string with depths and time
            decompression += depths[i] + ": " + tableRow.get(i) + "min, ";
            if (i == tableRow.size() - 1) {
                //delete extra comma from end of string
                decompression = decompression.substring(0, decompression.length() - 2);
            }
        }
        return decompression;
    }
}
