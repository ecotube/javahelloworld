package me.dante.adventure;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class PostPrinter {

  public static void main(String[] args) throws Exception{

    // 初始化链接
    // 进行数据库操作 执行sql语句
    // 释放资源（java 垃圾回收不及时）

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {

      connection  = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "123456");
      statement = connection.createStatement();
      resultSet = statement.executeQuery("select * from `post` order by id desc");
      while(resultSet.next()){
        System.out.println(resultSet.getString("author") + ":");
        System.out.println("\t" + resultSet.getString("body") + " .");
        System.out.println("==============================");
      }

      // executeUpdate 更新数据
      // java.sql.PreparedStatement 处于安全和性能考虑用这个！
      // 通过sql lib 进行转义等处理
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO (`body`, `author`) values (?, ?)");
      preparedStatement.setString(1, "Hello, World!");
      preparedStatement.setString(2, "Pete");
      preparedStatement.executeUpdate();
      preparedStatement.executeQuery();

    }finally{

      if (resultSet != null) {
          try {
              resultSet.close();
          }catch(Exception ignored){}
      }
      if (statement != null) {
          try {
              statement.close();
          }catch(Exception ignored){}
      }
      if (connection != null) {
          try {
              connection.close();
          }catch(Exception ignored){}
      }

    }

  }
}
