package com.data.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.List;
 import java.util.UUID;

@Entity
@Table(name = "user_application")
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 2, max = 100)
     private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 200)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneInfoEntity> phones;

    private OffsetDateTime created;
    private OffsetDateTime modified;
    private OffsetDateTime lastlogin;
    private String token;
    private boolean isactive;

    public UserInfoEntity() {
        OffsetDateTime now = OffsetDateTime.now();
        this.created = now;
        this.modified = now;
        this.lastlogin = now;
        this.isactive = true;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<PhoneInfoEntity> getPhones() { return phones; }
    public void setPhones(List<PhoneInfoEntity> phones) {
        this.phones = phones;
        if (phones != null) {
            phones.forEach(phone -> phone.setUser(this));
        }
    }

    public OffsetDateTime getCreated() { return created; }
    public void setCreated(OffsetDateTime created) { this.created = created; }

    public OffsetDateTime getModified() { return modified; }
    public void setModified(OffsetDateTime modified) { this.modified = modified; }

    public OffsetDateTime getLastLogin() { return lastlogin; }
    public void setLastLogin(OffsetDateTime lastLogin) { this.lastlogin = lastLogin; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public boolean isActive() { return isactive; }
    public void setActive(boolean active) { isactive = active; }
}
