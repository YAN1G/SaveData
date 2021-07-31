import dao.ElementsDao;
import java.sql.*;
import db.DBHelper;
import db.HbaseUtils;
import utils.GetObject;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.MD5;

public class Main {
//    public static void main(String[] args) {
//        ElementsDao elementsDao=new ElementsDao();
//        System.out.println(elementsDao.paramsSelect());
//
//    }
static String sql = null;
    static DBHelper db1 = null;
    static ResultSet ret = null;

    public static void main(String[] args) throws Exception{

        sql = "select *from same";//SQL语句
        db1 = new DBHelper(sql);//创建DBHelper对象

        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {

                String params = ret.getString(1);
                String create_time = ret.getString(2);
                create_time=create_time.substring(0,19);
                System.out.println(create_time);
                JSONObject dataJson = new JSONObject(params);
                //JSONObject data=new JSONObject(create_time);
                //JSONObject dataJson = new JSONObject(params);
                GetObject a=new GetObject();
                String idNo=a.Getstring(dataJson, "idNo");
                String mdn=a.Getstring(dataJson, "mdn");
                //String time=a.Getstring(data,"create_time");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //String sd = sdf.format(new Date().);
                Date date =  sdf.parse(create_time);
                long timeconvert = date.getTime();
                String timestamp = String.valueOf(timeconvert);
                System.out.println(timestamp);
                String idType=a.Getstring(dataJson, "idType");
                String encrypt=a.Getstring(dataJson, "encrypt");
                String name=a.Getstring(dataJson, "name");
//                HbaseUtils.getInstance().put("test2",mdn,"info","idType",idType);
//                HbaseUtils.getInstance().put("test2",mdn,"info","idNo",idNo);
//                HbaseUtils.getInstance().put("test2",mdn,"info","encrypt",encrypt);
//                HbaseUtils.getInstance().put("test2",mdn,"info","name",name);
                if(mdn.length()>16) {
                    System.out.println(mdn);
                    HbaseUtils.getInstance().put("THREEELEMENTS", mdn, "info", "create_time",timestamp);
                    HbaseUtils.getInstance().put("THREEELEMENTS", mdn, "info", "idType", idType);
                    HbaseUtils.getInstance().put("THREEELEMENTS", mdn, "info", "idNo", idNo);
                    HbaseUtils.getInstance().put("THREEELEMENTS", mdn, "info", "encrypt", encrypt);
                    HbaseUtils.getInstance().put("THREEELEMENTS", mdn, "info", "name", name);
                } else if("sha256"==encrypt){
                    System.out.println(mdn);
                }else{
                    HbaseUtils.getInstance().put("THREEELEMENTS",MD5.getInstance().getMD5(mdn),"info","create_time",timestamp);
                    HbaseUtils.getInstance().put("THREEELEMENTS",MD5.getInstance().getMD5(mdn),"info","idType",idType);
                    HbaseUtils.getInstance().put("THREEELEMENTS",MD5.getInstance().getMD5(mdn),"info","idNo",MD5.getInstance().getMD5(idNo));
                    HbaseUtils.getInstance().put("THREEELEMENTS",MD5.getInstance().getMD5(mdn),"info","encrypt",encrypt);
                    HbaseUtils.getInstance().put("THREEELEMENTS",MD5.getInstance().getMD5(mdn),"info","name",MD5.getInstance().getMD5(name));
                }

            }//显示数据
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
