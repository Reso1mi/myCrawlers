package util;

import handle.IResultSetHandle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBTemplate {
    public static int update(String sql, Object... obj) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBUtil.getCon();
            st = con.prepareStatement(sql);
            for (int index = 0; index < obj.length; index++) {
                st.setObject(index + 1, obj[index]);
            }
            return st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, st, null);
        }
        return 0;
    }

    public static <T>T Query(String sql, IResultSetHandle<T> rsh, Object... obj) { //重载太麻烦了，若为查询所有就传递null就可以了
        //声明要关闭的资源
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getCon();
            st = con.prepareStatement(sql);
            for(int index=0;index<obj.length;index++) {
                st.setObject(index+1, obj[index]);
            }
            rs = st.executeQuery(); //用预编译PrepareStatement没有参数，否则就是父类Statement的方法
            return rsh.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, st, rs);
        }
        throw new RuntimeException("查询失败");
    }
}
