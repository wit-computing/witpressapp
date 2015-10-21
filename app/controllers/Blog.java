package controllers;

import java.util.List;

import models.Comment;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class Blog  extends Controller
{
  public static void index()
  {
    User user = Accounts.getLoggedInUser();
    render(user);
  }
  
  public static void newPost(String title, String content)
  {
    User user = Accounts.getLoggedInUser();
    
    Post post = new Post (title, content);
    post.save();
    user.posts.add(post);
    user.save();
    
    Logger.info ("title:" + title + " content:" + content);
    index();
  }

  public static void deletePost(Long postid)
  {    
    User user = Accounts.getLoggedInUser(); 
    Post post = Post.findById(postid);
    Logger.info ("Request to delete title:" + post.title + " content:" + post.content);

    user.posts.remove(post);

    user.save();
    post.delete();

    index();
  }
  
  public static void publicBlog(Long userid)
  {
    User loggedInUser = null;
    String userId     = session.get("logged_in_userid");
    if (userId != null)
    {
      loggedInUser = User.findById(Long.parseLong(userId));
    }
    
    User user = User.findById(userid);
    render(user, loggedInUser);
  }
   
  public static void newComment(Long userid, Long postid, String content)
  {    
    Logger.info("Post ID = " + postid);
    Post post = Post.findById(postid);
    
    User user = Accounts.getLoggedInUser();
    Comment comment = new Comment(user, content);    
    post.comments.add(comment);
    post.save();
    publicBlog(userid);
  }  
}