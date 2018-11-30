package parse;

import dao.DoutuDao;
import model.DoutuModel;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.JsoupUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DouTuParse {
    public static List<DoutuModel> getData(String url) throws IOException {
        //获取的数据，存放在集合中
        List<DoutuModel> data = new ArrayList();
        //采用Jsoup解析
        //先获取所有的分页的链接   String url="https://doutushe.com/portal/index/index/p/1";
        for (int i = 1; i <= 28; i++) {
            //这里有点low等下再来优化
            String pageUrl = url + "/portal/index/index/p/" + i;
            Document doc = JsoupUtils.getHtmlDocument(pageUrl);
            //获取html标签中的内容
            Elements elements = doc.select("li[class=list-group-item]");
            for (Element element : elements) {
                //所有的主题的链接
                String topicUrl = url + element.select("p").select("a").attr("href");

                //主题名字
                String topic = element.select("h4").select("a").text();

                //每个主题下的所有图片
                Document topicDocument = JsoupUtils.getHtmlDocument(topicUrl);
                Elements imgList = topicDocument.select("div[class=col-xs-12 col-sm-8 col-lg-9]").select("img.lazy");
                for (Element imgelement : imgList) {
                    //异步的坏处体现出来了,这个明显是懒加载,要找就找数据源,直接获取src获取不到
                    //String imgUrl= imgelement.attr("src");
                    String imgUrl = imgelement.attr("data-original");
                    String title = imgelement.attr("title");
                    data.add(new DoutuModel(topic, imgUrl, title));
                }
                //System.out.println(topic+":"+imgurl);
            }
        }
        //返回数据
        return data;
    }

    public static List<DoutuModel> getData2(String url) throws Exception {
        //获取的数据，存放在集合中
        List<DoutuModel> data = new ArrayList();
        //采用Jsoup解析
        //String url="https://doutushe.com/portal/article/index/id/5gK";
        String preurl = "https://doutushe.com";
        //取到当前页的document
        //取到内容页的所有图片
        int page = 1;
        while (true) {
            Document doc = JsoupUtils.getHtmlDocument(url);
            Elements imgList = doc.select("div[class=col-xs-12 col-sm-8 col-lg-9]").select("img.lazy");
            String topic = doc.select("blockquote>p").text();
            for (Element imgelement : imgList) {
                //异步的坏处体现出来了,这个明显是懒加载,要找就找数据源,直接获取src获取不到
                //String imgUrl= imgelement.attr("src");
                String imgUrl = imgelement.attr("data-original");
                String title = imgelement.attr("title");
                data.add(new DoutuModel(topic, imgUrl, title));
                //System.out.println(topic + ":" + imgUrl + ":" + title);
            }
            Elements pageUrls = doc.select("ul.pager").select("a");
            //爬一页休息1秒
            if (page % 10 == 0) {
                Thread.sleep(1000);
                System.out.println("第" + (page/10) + "页采集完 , 暂停-------");
            }
            //最后一页也有两个按钮。。。看来要多观察页面
            /*if (pageUrls.size() < 2) {
                //说明到最后一页了
                break;
            }*/
            url = preurl + pageUrls.get(1).attr("href");
            if (!url.matches(preurl + "/portal/article/index/id/[a-zA-Z0-9_]*")) {
                break;
            }
            page++;
        }
        //返回数据
        return data;
    }
}
