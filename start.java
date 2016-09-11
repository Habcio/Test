package app_zaliczenie;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class start {

    public static void main(String[] args) throws FileNotFoundException {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        List<Future<String>> resultList = new ArrayList<>(); // tablica

        int x = 0;
        int sumax = 0;
        while (x < 999) {
            x++;

            String plik = Integer.toString(x);
            mat www = new mat(plik);
            Future<String> wynik = executor.submit(www);
            resultList.add(wynik);

            if (resultList.size() == 20) {
                for (Future<String> future : resultList) {
                    try {
                        System.out.println(future.get());
                        sumax = Integer.parseInt(future.get());
                        // sumowanie puli x co 20
                    } catch (InterruptedException | ExecutionException e) {
                        // e.printStackTrace();
                        System.out.println(plik + " - Blad");
                    }
                }
                System.out.println("********");
                resultList.clear();
            }
        }

        // --------yyy
        int y = 0;
        int sumay = 0;
        while (y < 499) {
            y++;

            String plik = Integer.toString(y);
            maty www = new maty(plik);
            Future<String> wynik = executor.submit(www);
            resultList.add(wynik);

            if (resultList.size() == 20) {
                for (Future<String> future : resultList) {
                    try {
                        System.out.println(future.get());
                        sumay = Integer.parseInt(future.get());
                        // sumowanie puli y co 20
                    } catch (InterruptedException | ExecutionException e) {
                        // e.printStackTrace();
                        System.out.println(plik + " - Blad");
                    }
                }
                System.out.println("********");
                resultList.clear();
            }
        }

        System.out.println("Suma x:= " + sumax);
        System.out.println("Suma y:= " + sumay);
        System.out.println("Suma z = x-y:= " + (sumax-sumay));
       

        System.out.println("zakonczono sprawdzanie");
        executor.shutdown();

    }
}