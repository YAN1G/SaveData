package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.MysqlDBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
public class ElementsDao {
    public List<String> paramsSelect(){
        QueryRunner queryRunner = new QueryRunner();
        Connection conn = null;
        List<String> list = null;
        String sql = "SELECT params from same where create_time='2021-06-03 00:15:56.266'";
        try {
            conn = MysqlDBUtil.getConnection();
            list = queryRunner.query(conn,sql, new ColumnListHandler<String>());
        } catch (SQLException e) {
            //printErrorLog(e);
        }finally {
            try {
                MysqlDBUtil.close(conn);
            } catch (SQLException e) {
                //printErrorLog(e);
            }
        }
        return list;
    }
}
