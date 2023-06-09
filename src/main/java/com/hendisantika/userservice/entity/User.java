package com.hendisantika.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : user-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/10/20
 * Time: 22.43
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 65981149772133526L;
    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "PROVIDER_USER_ID")
    private String providerUserId;
    private String email;
    @Column(name = "enabled", columnDefinition = "BIT", length = 1)
    private boolean enabled;
    @Column(name = "DISPLAY_NAME")
    private String displayName;
    private String password;

    private String provider;

    // bi-directional many-to-many association to Role
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns =
            {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = Borrow.class)
    Set Borrow = new HashSet();
}
