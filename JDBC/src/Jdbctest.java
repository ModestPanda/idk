import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbctest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        //String sql="insert into news values(null,'辛弃疾','2009-11-11')";
        //String sql="update news set name='陶渊明' where id=1";
        String sql="delete from news where id=3";
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);//如果是dml语句，返回的就是影响的行数

        System.out.println(rows>0?"成功":"失败");
        //4.关闭连接资源
        statement.close();
        connection.close();
    }
}
