package twijava.http.requests;

import twijava.api.URLsUtil;
import twijava.http.HttpRequest;
import twijava.http.HttpRequestComponents;

import java.util.List;
import java.util.TreeMap;

public class TwitterPostTweetRequest implements HttpRequestComponents {

    @Override
    public String getMethod(){
        return "POST";
    }

    @Override
    public HttpRequest http(){
        return new HttpRequest();
    }

    public String updateStatusRequest(String requestUrl, List<String> keylist, TreeMap<String, String> data) {

        String url = URLsUtil.END_POINT_URL + requestUrl;

        return http().requestToAPI(getMethod(), url, data, keylist);
    }
}