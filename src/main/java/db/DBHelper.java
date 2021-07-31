package db;

import java.sql.*;


public class DBHelper {
    public static final String url = "jdbc:mysql://39.104.86.153:3306/sanys_api_v2_00?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "yxc";
    public static final String password = "Y12345678";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url,user,password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
