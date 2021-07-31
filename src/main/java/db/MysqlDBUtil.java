package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class MysqlDBUtil {

    public static Connection getConnection() throws SQLException {
        DBPool pool = DBPool.getInstance();
        return pool.getConnection();
    }
    public static void close(Connection conn,PreparedStatement ps){
        try {
            DbUtils.close(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            DbUtils.close(conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
        try {
            DbUtils.close(rs);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            DbUtils.close(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            DbUtils.close(conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void close(Connection conn) throws SQLException{
        DbUtils.close(conn);
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
}

