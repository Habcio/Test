package app_zaliczenie1;

import java.util.List;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class main {
    
    public static void main(String[] args) throws IOException {
        String strLine;
        System.out.println("Pobieram strony");    
    
        //otworz plik
        FileInputStream fstream = new FileInputStream("hosts.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        //Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //create a list to hold the Future object associated with Callable
        List<Future<String>> list = new ArrayList<Future<String>>();
       int i = 0;
       int j = 0;
      //Czytaj Linie
              while ((strLine = br.readLine()) != null)   {           
                  if(i<5){
                      //Create MyCallable instance
                       Callable<String> callable = new NetTester(strLine);
                    //submit Callable tasks to be executed by thread pool
                    Future<String> future = executor.submit(callable);
                    //add Future to the list, we can get return value using Future
                    list.add(future);
                    i++;
                  }else{
                      //wyswietlanie wszystkich 5 hostow
                      for(Future<String> fut : list){
                          j++;
                          try {
                              System.out.println(j+ " :: "+fut.get());
                          } catch (InterruptedException | ExecutionException e) {
                              e.printStackTrace();
                          }
                      }
                      System.out.println("==Kolejna pi¹tka adresów===");
                      list.clear();                  
                      i=0;
                      j=0;
                  }
              }
                  
        //shut down the executor service now
        executor.shutdown();
        System.out.println("==Koniec sprawdzania==");
        
      //Close the input stream
              br.close();
    }

                
    }