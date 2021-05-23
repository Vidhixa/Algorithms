/*
Write a function that finds anyone who badged into the room three or more times in a one-hour period.
*/

import java.io.*;
import java.util.*;

public class BadgeAlert {
    public static void main(String[] argv) {
        String[][] badgeTimes = new String[][] {
                {"Paul",     "1355"},
                {"Jennifer", "1910"},
                {"John",      "835"},
                {"John",      "830"},
                {"Paul",     "1315"},
                {"John",     "1615"},
                {"John",     "1640"},
                {"Paul",     "1405"},
                {"John",      "855"},
                {"John",      "930"},
                {"John",      "915"},
                {"John",      "730"},
                {"John",      "940"},
                {"Jennifer", "1335"},
                {"Jennifer",  "730"},
                {"John",     "1630"},
                {"Jennifer",    "5"}
        };

        Map<String, List<Integer>> badgeTSMap = new HashMap<>();
        populateBadgeTS(badgeTSMap, badgeTimes);

        List<EmpTS> result = findLateBadgers(badgeTSMap);
        for(EmpTS e: result) {
            Collections.sort(e.ts);
            System.out.println(e.emp +  " : " + e.ts);
        }
    }

    public static void populateBadgeTS(Map<String, List<Integer>> badgeTSMap, String[][] badgeTimes) {
        for(String[] s: badgeTimes) {
            if(!badgeTSMap.containsKey(s[0]))
                badgeTSMap.put(s[0], new ArrayList<>());
            List<Integer> vals = badgeTSMap.get(s[0]);
            vals.add(Integer.parseInt(s[1]));
            badgeTSMap.put(s[0], vals);
        }
    }

    public static List<EmpTS> findLateBadgers(Map<String, List<Integer>> badgeTsMap) {
        List<EmpTS> result = new ArrayList<>();

        for(Map.Entry<String, List<Integer>> e: badgeTsMap.entrySet()) {
            List<Integer> values = e.getValue();
            Set<Integer> empResult = new HashSet<>();
            Collections.sort(values);
            if(values.size() >= 3) {
                for(int i=0; i<values.size()-2; i++) {
                    if(values.get(i+2)-values.get(i) <= 100) {
                        empResult.add(values.get(i));
                        empResult.add(values.get(i+1));
                        empResult.add(values.get(i+2));
                    }
                }
            }
            if(empResult.size()> 0) {
                EmpTS empTs = new EmpTS(e.getKey(), new ArrayList<Integer>(empResult));
                result.add(empTs);
            }
        }
        return result;
    }
}

class EmpTS {
    String emp;
    List<Integer> ts;

    EmpTS(String name, List<Integer> ts) {
        this.emp = name;
        this.ts = ts;
    }

}
/*
Output
=======
John : [830, 835, 855, 915, 930, 940, 1615, 1630, 1640]
Paul : [1315, 1355, 1405]
*/
