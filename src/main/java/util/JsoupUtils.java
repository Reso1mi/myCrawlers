package util;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupUtils {

  /*  public static HttpResponse getRawHtml(HttpClient client, String personalUrl) {
        //获取响应文件，即html，采用get方法获取响应数据
        HttpGet getMethod = new HttpGet(personalUrl);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        try {
            //执行get方法
            response = client.execute(getMethod);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // getMethod.abort();
        }
        return response;
    }
*/
    public static Document getHtmlDocument(String url) throws IOException {
        Connection connect = Jsoup.connect(url);
        return connect.get();
    }
}
