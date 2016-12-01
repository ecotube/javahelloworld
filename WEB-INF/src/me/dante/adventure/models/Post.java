package me.dante.adventure.models;

import java.util.Date;

public class Post{

  private String body;
  private String author;
  private Integer id;
  private Date created_at;

  public void setBody(String body){
    this.body = body;
  }

  public void setAuthor(String author){
    this.author = author;
  }

  public void setId(Integer id){
    this.id = id;
  }

  public void setCreatedAt(Date created_at){
    this.created_at = created_at;
  }

  public String getBody(){
    return this.body;
  }

  public String getAuthor(){
    return this.author;
  }

  public Date getCreatedAt(){
    return this.created_at;
  }

  public Integer getId(){
    return this.id;
  }


}
