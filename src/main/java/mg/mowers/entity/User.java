package mg.mowers.entity;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dim_user") // Use the correct table name from the SQL script
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID") // Map to the primary key column in the database
    private Long id;

    @Column(name = "name") // Map to the corresponding columns in the database
    private String name;

    @Nullable
    @Column(name = "surname")
    private String surname;

    @Nullable
    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    // Assuming there is a default role for users
    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    // Constructors, getters, and setters
    // Constructors, getters, and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public User() {
    }

    public User(String name, String surname, String avatarPath, String email, String passwordHash, Long roleId) {
        this.name = name;
        this.surname = surname;
        this.avatarPath = avatarPath;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
    }
}
