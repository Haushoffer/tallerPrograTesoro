package bo.ucbcba.taller.proyecto.proyecto.entities;
//import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Juan on 11/05/2017.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pattern="\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b";
    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}",flags={Pattern.Flag.CASE_INSENSITIVE} ,message = "Invalid Email Address.")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[0-9])[^ ]{8,}$",flags={Pattern.Flag.CASE_INSENSITIVE} ,message = "Password must be minimum 8 characters and at least 1 Alphabet and 1 Number.")
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;
    @DecimalMin(value = "0", message = "Please input a coherent weight")
    @DecimalMax(value = "350", message = "Please input a coherent weight")
    @NotNull(message="Please, input your weigth")
    private BigDecimal weigth;
    @DecimalMin(value = "0", message = "Please input a coherent height")
    @DecimalMax(value = "2.8", message = "Please input a coherent height")
    @NotNull(message="Please, input your heigth")
    private BigDecimal heigth;
    @NotNull(message="Please, input your age")
    private Integer age;
    @NotNull(message="Please, input your gender")
    private Boolean gender;
    private Set<Band> bands;
    @NotNull(message="Please, input your name")
    private String name;
    @NotNull(message="Please, input your last name")
    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Band> getBands() {
        return bands;
    }


    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public BigDecimal getWeigth() {
        return weigth;
    }

    public void setWeigth(BigDecimal weigth) {
        this.weigth = weigth;
    }

    public BigDecimal getHeigth() {
        return heigth;
    }

    public void setHeigth(BigDecimal heigth) {
        this.heigth = heigth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
