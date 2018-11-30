package boot;

import dao.DoutuDao;
import model.DoutuModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import parse.DouTuParse;

import java.util.List;

public class Spider {
    //log4j的是使用，不会的请看之前写的文章
    static final Log logger = LogFactory.getLog(Spider.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="https://doutushe.com/portal/article/index/id/5gK";
        //抓取的数据
        List<DoutuModel> doutuModels=DouTuParse.getData2(url);
        //循环输出抓取的数据
        /*for (DoutuModel doutuModel:doutuModels) {
            logger.info("Topic:"+doutuModel.getTopic()+"\t"+"title:"+doutuModel.getTitle()+"\t"+"imgUrl:"+doutuModel.getImgUrl());
        }*/
        //将抓取的数据插入数据库
       //BookDao.save(doutuModels);
        DoutuDao.save(doutuModels);
    }
}
