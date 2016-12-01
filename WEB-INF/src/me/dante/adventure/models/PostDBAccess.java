package me.dante.adventure.models;

import me.dante.adventure.models.DBAction;
import me.dante.adventure.models.Post;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class PostDBAccess extends DBAction {

  @Override
  public void prepareQuerySql() throws Exception{
    preparedStatement = connection.prepareStatement("SELECT * FROM `posts` ORDER BY `created_at` DESC;");
  }

  @Override
  public void prepareUpdateSql(Map<String, String> params) throws Exception{
    preparedStatement = connection.prepareStatement("INSERT INTO `posts` (`body`, `author`, `created_at`) values (?, ?, ?)");
    preparedStatement.setString(1, params.get("body"));
    preparedStatement.setString(2, params.get("author"));
    // java.util.Date now = new java.util.Date();
    // Timestamp stamp = new java.sql.Timestamp(now.getTime());
    preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public List<Post> retrieveData() throws Exception{
    List<Post> posts = new ArrayList<Post>();
    while(resultSet.next()) {
      Post post = new Post();
      post.setId(resultSet.getInt("id"));
      post.setAuthor(resultSet.getString("author"));
      post.setBody(resultSet.getString("body"));
      post.setCreatedAt(resultSet.getTimestamp("created_at"));
      posts.add(post);
    }
    return posts;
  }

}
