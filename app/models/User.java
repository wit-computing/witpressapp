package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import play.db.jpa.Model;
import play.db.jpa.Blob;

@Entity
public class User extends Model
{
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  public boolean online;
  
  @OneToMany(cascade = CascadeType.ALL)
  public List<Post> posts = new ArrayList<Post>();
  
  public User(String firstName, String lastName, String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }
  
  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }  
}