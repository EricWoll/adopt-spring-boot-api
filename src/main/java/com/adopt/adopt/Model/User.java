package com.adopt.adopt.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {
    @MongoId
    private ObjectId id;
    private String userId;
    @NotBlank
    @Size(max=30)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min=8)
    private String password;
    private ERole role;

    public User(
            String username,
            String email,
            String password,
            ERole role
    ) {
        // Don't use an ID.... the username and email are always checked for uniqueness anyway
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
}
