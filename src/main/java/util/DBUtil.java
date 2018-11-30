package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Properties p = new Properties(); //资源对象
    //创建数据源对象（连接池）
    static DataSource bds = null;
    static { //静态代码块
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
            p.load(in);
            bds = DruidDataSourceFactory.createDataSource(p);//这样写要保证配置文件里不能乱写
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDriverClassName() {
        return p.getProperty("DriverClassName");
    }

    public static String getUrl() {
        return p.getProperty("url");
    }

    public static String getUsername() {
        return p.getProperty("username");
    }

    public static String getPassword() {
        return p.getProperty("password");
    }

    public static Connection getCon() {
        try {
            return DriverManager.getConnection(DBUtil.getUrl(), DBUtil.getUsername(), DBUtil.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection con, Statement st, ResultSet rs) {
        //5.事
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}
