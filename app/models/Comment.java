package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model
{
  @Lob
  public String content;
  
  @OneToOne 
  public User author;
  
  public Date postedAt;
 
  public Comment(User author, String content)
  {
    this.author   = author;
    this.content  = content;
    this.postedAt = new Date();
  }
}