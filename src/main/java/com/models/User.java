package com.models;

import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable, UserDetails {

    private static final long serialVersionUID = 2740861780155026331L;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String username;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "image_path")
    @Lob
    private byte[] imagePath;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns        = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Mail> mails;

    public User() {
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getImagePath() {
        return imagePath;
    }

    public void setImagePath(byte[] imagePath) {
        this.imagePath = imagePath;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof User) {
            final User other = (User) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getUsername(), other.getUsername())
                    && Objects.equal(getPassword(), other.getPassword())
                    && Objects.equal(getEnabled(), other.getEnabled());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUsername(), getPassword(), getEnabled());
    }

    @Transient
    public Set<Permission> getPermissions() {
        Set<Permission> perms = new HashSet<Permission>();
        for (Role role : roles) {
            perms.addAll(role.getPermissions());
        }
        return perms;
    }

    @Override
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.addAll(getRoles());
        authorities.addAll(getPermissions());
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        //return true = account is valid / not expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return true = account is not locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return true = password is valid / not expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }

    public String getRolename() {
        String result = "";
        for (Role role : roles) {
            result = role.getRolename();
            break;
        }
        return result;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", enabled=" + enabled +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", roles=" + roles +
                '}';
    }


}
