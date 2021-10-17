package htl.grieskirchen.koglerm190074.Beispiel1;

import com.sun.source.util.TaskEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static List<String[]> list = new ArrayList<>();
    public static List<String> stringList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        List<Integer> integerList = readCSV("numbers.csv");
        //System.out.println(integerList.size());
        System.out.println("chunks:");
        int chunks = Integer.parseInt(s.nextLine());
        System.out.println("divider");
        int divider = Integer.parseInt(s.nextLine());

        int laenge = integerList.size() / chunks;

        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(8);

        int count = integerList.size()/chunks;
        int obergrenze = count;
        int untergrenze = 0;

        for (int i = 0; i < chunks; i++) {

            List<Integer> intList = integerList.subList(untergrenze,obergrenze);

            executor.execute((() -> {
                for (int j = 0; j < intList.size(); j++) {
                    if (intList.get(j) % divider == 0) {
                        System.out.println(intList.get(j));
                    }
                }
            }));
            untergrenze += count;
            obergrenze += count;
        }
        executor.shutdown();
    }

    public static List<Integer> readCSV(String name) {

        List<Integer> intList = new ArrayList<>();
        File file = new File(name);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(name));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                list.add(zeile.split(":"));

                // System.out.println(zeile);
            }
            for (String[] sArr:list) {
                for (String s:sArr) {
                    stringList.add(s);
                }
            }
            for (String s : stringList) {
                boolean isInt = true;
                int count = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (!Character.isDigit(s.charAt(i))) {
                        isInt = false;
                        count++;
                    }
                    if (i == s.length() - 1) {
                        if (count == 0) {
                            //System.out.println(Integer.parseInt(s));
                            intList.add(Integer.parseInt(s));
                        }
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return intList;
    }


}
