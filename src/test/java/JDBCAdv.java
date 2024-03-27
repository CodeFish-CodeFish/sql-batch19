import org.junit.Assert;
import org.junit.Test;
import utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCAdv extends JdbcUtils{
    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:oracle:thin:@database-1.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                        "student","codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();
//        resultSet.next();

//        for (int i = 1 ; i <= metaData.getColumnCount(); i++){
//            rowMap.put(metaData.getColumnName(i), resultSet.getObject(i));
//        }
//        System.out.println(rowMap);
//        System.out.println(rowMap.get("LAST_NAME"));

        List<Map<String, Object>> empResultSet = new ArrayList<>();

        while (resultSet.next()){
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1 ; i <= metaData.getColumnCount(); i++){
                rowMap.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            empResultSet.add(rowMap);
        }

        System.out.println(empResultSet.get(0));
        System.out.println(empResultSet.get(1));
        System.out.println(empResultSet.get(2));





//        while (resultSet.next()){

//        }

    }


    @Test
    public void test2() throws SQLException {
        ResultSet resultSet = JdbcUtils.queryDB("Select * from employees");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnCount());

        // validate TJ salary is 2100

        List<Map<String,Object>> empResultSet = new ArrayList<>();

        while (resultSet.next()){
            Map<String,Object> rowMap = new HashMap<>();
            for (int i = 1; i<= resultSetMetaData.getColumnCount(); i++){
                rowMap.put(resultSetMetaData.getColumnName(i) /* columnName*/
                        , resultSet.getObject(i)) /* columnValue*/;
            }
            empResultSet.add(rowMap); // adding every row in to list
        }

        System.out.println(empResultSet.size());

        for(Map<String, Object> eachMap : empResultSet){
            String firstName = String.valueOf(eachMap.get("FIRST_NAME"));
            if (firstName.equalsIgnoreCase("TJ")){
                int salary = Integer.parseInt(eachMap.get("SALARY").toString());
                Assert.assertEquals(2100, salary);
            }
        }

    }



}
