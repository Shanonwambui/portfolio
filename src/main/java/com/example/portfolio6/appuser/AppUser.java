package com.example.portfolio6.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import jakarta.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {
    @SequenceGenerator(
            name = "investor_sequence",
            sequenceName = "investor_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "investor_sequence"
    )
    private  Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private  Boolean enabled = false;
    public AppUser (String firstName,
                    String lastName,
                    String email,
                    String password,
                    AppUserRole appUserRole){
        this.firstName= firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        this.appUserRole=appUserRole;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUserName(){
        return email;
    }
    @Override
    public String getFirstName(){
        return firstName;
    }
    @Override
    public String getLastName(){
        return lastName;
    }
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        return !locked;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @Override
    public boolean isEnabled(){
        return enabled;
    }

}
