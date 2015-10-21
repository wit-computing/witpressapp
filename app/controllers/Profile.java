package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Profile extends Controller
{
  public static void change (String firstName,   String lastName, String email,    String password, String password2)
  {
    User user = Accounts.getLoggedInUser();
    user.firstName = firstName;
    user.lastName = lastName;
    user.email = email;
    user.password = password;
    user.save();
    Blog.index();
  }

  public static void index()
  {
    User user = Accounts.getLoggedInUser();
    render(user);
  }
}