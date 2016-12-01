package me.dante.adventure.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public abstract class DBAction {

  protected Connection connection = null;
  protected PreparedStatement preparedStatement = null;
  protected ResultSet resultSet = null;

  final public <T> List<T> getData() throws Exception{
    List<T> data = new ArrayList<T>();
    try{
      Class.forName("com.mysql.jdbc.Driver");
      initConnection();
      prepareQuerySql();
      resultSet = preparedStatement.executeQuery();
      data = retrieveData();
    }finally{
      destroy();
    }
    return data;
  }

  final public void updateData(Map<String, String> params) throws Exception{
    try{
      Class.forName("com.mysql.jdbc.Driver");
      initConnection();
      prepareUpdateSql(params);
      preparedStatement.executeUpdate();
    }finally{
      destroy();
    }
  }

  final public void initConnection() throws Exception{
    connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "javaroot", "123456");
  }

  final public void destroy(){
    if (resultSet != null) {
        try {
            resultSet.close();
        }catch(Exception ignored){}
    }
    if (preparedStatement != null) {
        try {
            preparedStatement.close();
        }catch(Exception ignored){}
    }
    if (connection != null) {
        try {
            connection.close();
        }catch(Exception ignored){}
    }
  }

  abstract public void prepareQuerySql() throws Exception;
  abstract public void prepareUpdateSql(Map<String, String> params) throws Exception;
  abstract public <T> List<T> retrieveData() throws Exception;


}
