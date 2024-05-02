package model;

import com.mysql.jdbc.PreparedStatement;

import javax.persistence.*;
import java.sql.*;



@Entity
@Table(name = "user", schema = "USERS1")
public class UserEntity {
    private static Connection connection = DBConnection.getConnection();;

    private Integer idUser;
    private String name;
    private String password;
    private String email;


    public UserEntity() throws SQLException {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity(Integer id, String name, String password, String email) {
        this.idUser = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
    public static boolean isUserExists(String username) {
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE name=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static boolean registerUser(String username, String password, String email) {
        try {
            java.sql.PreparedStatement ps =  connection.prepareStatement("INSERT INTO user (name, password, email) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            int result = ps.executeUpdate();
            return result > 0; // Если запись добавлена успешно, возвращаем true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

