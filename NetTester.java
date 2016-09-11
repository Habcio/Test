package app_zaliczenie1;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

class  NetTester implements Callable<String> {

    public String url; 
    public NetTester(String urll){
        this.url = urll;
    }
    
    @Override
    public synchronized String call() throws Exception {
        
    try {
        HttpURLConnection connection = (HttpURLConnection) new URL(this.url).openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {                
            return connection.getURL() + " Dost�pna";
            
        }else{
            return connection.getURL() + " Niedost�pna!";
        }
    } catch (Exception e) {
        return "Nie mo�na pobra� adresu";
    }
        
        
    
    }


    
    
}