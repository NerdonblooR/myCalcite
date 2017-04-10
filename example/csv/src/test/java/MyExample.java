import java.sql.*;

/**
 * Created by haotan on 3/11/17.
 */
public class MyExample {

    public static void main(String[] args) throws Exception {
        new MyExample().run();
    }

    public void run() throws ClassNotFoundException, SQLException {
        //if using external model file, MySql driver will not be loaded
        Connection connection =
                DriverManager.getConnection("jdbc:calcite:model=/Users/haotan/Desktop/SparkCollocated/tpchModel.jason");
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("select * from customer");
        final StringBuilder buf = new StringBuilder();
        while (resultSet.next()) {
            int n = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= n; i++) {
                buf.append(i > 1 ? "; " : "")
                        .append(resultSet.getMetaData().getColumnLabel(i))
                        .append("=")
                        .append(resultSet.getObject(i));
            }
            System.out.println(buf.toString());
            buf.setLength(0);
        }
        resultSet.close();
        statement.close();
        connection.close();

    }


}
