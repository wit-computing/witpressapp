import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Comment;
import models.Post;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class CommentTest  extends UnitTest
{
  private User bob;
  private Post aPost;
  
  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }

  @Before
  public void setup()
  {
    bob   = new User("bob", "jones", "bob@jones.com", "secret");
    aPost = new Post ("Post A", "This is the post A content");
    bob.posts.add(aPost);
    aPost.save();
    bob.save();
  }
  
  @After
  public void teardown()
  {
    bob.delete();
  }
  
  @Test
  public void testAddComment()
  {
    User user = User.findByEmail("bob@jones.com");
    
    Comment comment1 = new Comment(user, "Comment 1");
    comment1.save();
    
    Post post = user.posts.get(0);
    post.comments.add(comment1);
    post.save();
    user.save();
    
    User anotherUser = User.findByEmail("bob@jones.com");
    assertEquals("Comment 1", anotherUser.posts.get(0).comments.get(0).content);
  }
  
  @Test
  public void testAddDeleteComment()
  {
    User user = User.findByEmail("bob@jones.com");
    Comment comment1 = new Comment(user, "Comment 1");
    comment1.save();
    

    Post post = user.posts.get(0);
    post.comments.add(comment1);
    post.save();
    user.save();
    
    User anotherUser = User.findByEmail("bob@jones.com");
    assertEquals("Comment 1", anotherUser.posts.get(0).comments.get(0).content);
    
    post.comments.clear();
    post.save();
    comment1.delete();
    
    User yetAnotherUser = User.findByEmail("bob@jones.com");
    assertEquals(0, yetAnotherUser.posts.get(0).comments.size());
  }
}