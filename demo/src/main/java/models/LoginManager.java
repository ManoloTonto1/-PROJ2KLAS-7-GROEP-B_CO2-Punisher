package models;
import com.example.App;
import java.sql.*;

public class LoginManager {
    private static LoginManager instance = null;

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    private User loginUser = null;


    public boolean Login(String email, String pincode){
        ResultSet result = App.db.query("SELECT id, email, pincode, naam, streak, level, scores, isAdmin, lastLoginDate FROM users");
        try {
            
            while (result.next())
            {
                if(email.equals(result.getString("email")) && pincode.equals(result.getString("pincode"))) {


                    // User(int ID, String naam, int streak, int level, int isAdmin)
                    int id = result.getInt("id");
                    String naam = result.getString("naam");
                    int streak = result.getInt("streak");
                    int level = result.getInt("level");
                    int scores = result.getInt("scores");
                    int isAdmin = result.getInt("isAdmin");
                    String lastLogin = result.getString("lastLoginDate");
                    
                    User user = new User(id, naam, streak, level, scores, isAdmin, lastLogin);

                    this.SetUser(user);
                    System.out.println("----------");
                    System.out.println("Logged in as: "+result.getString("naam"));
                    System.out.println("----------");
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("[i] "+ex);
        }
        return false;
    }

    public boolean Logout() {
        if(this.GetUser() != null) {
            this.SetUser(null);
            return true;
        }
        return false;
    }

    public void SetUser(User user){
        this.loginUser = user;
    }

    public User GetUser(){
        if(this.loginUser != null) {
            return this.loginUser;
        }
        return null;
    }
}
