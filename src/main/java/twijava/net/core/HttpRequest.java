package twijava.net.core;

import twijava.net.OAuthUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

public class HttpRequest {

    private static final String TWITTERAPI_BASEURL = "https://api.twitter.com/1.1/"; //common url of endpoint

    private static final String POST="POST";


      public String request(String method,String uri,String ck,String ac,String cks,String ats,
                             TreeMap<String,String>data)throws Exception{

        String url=TWITTERAPI_BASEURL+uri;

        TreeMap<String,String>oauthMap=OAuthUtil.getOAuthParam(ck,ac);

        String signature=OAuthUtil.generateSignature(method,url,data,oauthMap);
        String oAuthHeader=OAuthUtil.makeOAuthHeader(signature,oauthMap,cks,ats);
        String urlwithParam=OAuthUtil.makeURLwithParam(url,data);

        URL sendurl=new URL(urlwithParam);

        HttpURLConnection urlConnection = (HttpURLConnection) sendurl.openConnection();

        if(method.equals(POST)) {
            urlConnection.setRequestMethod(POST);
        }
        urlConnection.setRequestProperty("Authorization", oAuthHeader);
        urlConnection.connect();

          BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

          StringBuilder sb = new StringBuilder();

          String line;

          while ((line = br.readLine()) != null) {sb.append(line);}

          br.close();

          return sb.toString();
    }

}