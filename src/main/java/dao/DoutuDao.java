package dao;

import model.DoutuModel;
import util.DBTemplate;

import java.util.List;

public class DoutuDao {
    public static void save(List<DoutuModel> DoutuModels) {
        String sql = "insert into doutu_imgs (id,topic,imgUrl,title) values (?,?,?,?)";
        for (DoutuModel model : DoutuModels) {
            DBTemplate.update(sql, model.getId(), model.getTopic(), model.getImgUrl(), model.getTitle());
        }
    }
}
