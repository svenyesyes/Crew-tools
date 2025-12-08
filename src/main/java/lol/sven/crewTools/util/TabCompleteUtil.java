package lol.sven.crewTools.util;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteUtil {

    public static List<String> filterNames(List<String> players, String filter) {

        List<String> filtered = new ArrayList<>();

        for(String str : players) {
            if(str.toLowerCase().startsWith(filter.toLowerCase())) {
                filtered.add(str);
            }
        }

        return filtered;
    }

}
