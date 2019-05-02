import org.sql2o.Connection;

import java.util.List;

public class Forum {
    private int id;
    private String name;
    private int time;
    private String message;
    private String email;

    public Forum(String name, int time, String message, String email) {
        this.name = name;
        this.time = time;
        this.message = message;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public void save() {
        try(Connection con = DB.sql2o.open())  {
            String sql = "INSERT INTO forums (name,email,message,time) VALUES (:name,:email,:message,:time)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email",this.email)
                    .addParameter("message",this.message)
                    .addParameter("time",this.time)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Forum> all() {
        String sql = "SELECT * FROM forums";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Forum.class);
        }
    }

    @Override
    public boolean equals(Object otherForum){
        if (!(otherForum instanceof Forum)) {
            return false;
        } else {
            Forum newForum = (Forum) otherForum;
            return this.getId() == newForum.id;
        }
    }

}
