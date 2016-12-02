package me.dante.adventure.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public abstract class DAO {

  protected Connection connection = null;
  protected PreparedStatement preparedStatement = null;
  protected ResultSet resultSet = null;

  final public <T> List<T> getData() throws Exception{
    List<T> data = new ArrayList<T>();
    try{
      Class.forName("com.mysql.jdbc.Driver");
      init();
      prepareQuery();
      resultSet = preparedStatement.executeQuery();
      data = retrieveData();
    }finally{
      close();
    }
    return data;
  }

  final public void updateData(Map<String, String> params) throws Exception{
    try{
      Class.forName("com.mysql.jdbc.Driver");
      init();
      prepareUpdate(params);
      preparedStatement.executeUpdate();
    }finally{
      close();
    }
  }

  final public void init() throws SQLException{
    connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "javaroot", "123456");
  }

  final public void close(){
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

  abstract public void prepareQuery() throws SQLException;
  abstract public void prepareUpdate(Map<String, String> params) throws SQLException;
  abstract public <T> List<T> retrieveData() throws SQLException;


}
