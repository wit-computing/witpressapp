package controllers;

import java.util.List;
import models.Comment;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class BlogPost  extends Controller
{
  public static void view(Long postid)
  {
    User user = Accounts.getLoggedInUser();
    Logger.info("Post ID = " + postid);
    Post post = Post.findById(postid);
    render (user, post);
  }
  
  public static void newComment(Long postid, String content)
  {    
    Logger.info("Post ID = " + postid);
    Post post = Post.findById(postid);
    
    User user = Accounts.getLoggedInUser();
    Comment comment = new Comment(user, content);    
    post.comments.add(comment);
    post.save();
    view(postid);
  } 
}
