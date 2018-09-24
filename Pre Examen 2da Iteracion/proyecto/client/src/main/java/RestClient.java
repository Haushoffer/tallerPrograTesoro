import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class RestClient {

    public static void main(String[] args) {
        try {
            while(true) {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                int sleep;
                double meters= (Math.random() * 10000);
                long registration = System.currentTimeMillis();//tiempo de registro
                try {
                    sleep=(int) (Math.random() * 5000) + 1500;//duerme el sistema de 1.5 seg hasta 5 seg
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HttpPost postRequest = new HttpPost(
                        "http://localhost:8080/API/stepLog/" + registration+"/"+meters);
                ArrayList<NameValuePair> postParameters;
                int number = (int) (Math.random() * 10) + 1;//numero de pasos aleatorio

                int id = 2;//id de la banda
                System.out.println("quantity:    " + number + "    meters   "+meters + "  registration: " + registration + "   id:" + id);//imprime para control
                postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("quantity", number + ""));
                postParameters.add(new BasicNameValuePair("bandId", id + ""));
                postParameters.add(new BasicNameValuePair("meters", meters + ""));
                postRequest.setEntity(new UrlEncodedFormEntity(postParameters));
                postRequest.addHeader("accept", "application/json");
                HttpResponse response = httpClient.execute(postRequest);
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }
                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                httpClient.getConnectionManager().shutdown();
                try {
                    sleep=(int) (Math.random() * 5000) + 1500;//duerme el sistema de 1.5 seg hasta 5 seg
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}