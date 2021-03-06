package taxiservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public User(){

    }

    public User(User user){
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.active = user.active;
    }

    public User(String username, String password, boolean active, Person person, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.person = person;
        this.roles = roles;
    }

    public User(String username, String password, boolean active, Person person) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.person = person;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

   public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                //", person=" + person.getId() +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(person, user.person) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, active, person, roles);
    }

    public static void findTaxist(Iterable<User> users){ //доделать и проверить
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User curUser = iterator.next();
            if(!curUser.getRoles().contains(Role.TAXIDRIVER)) iterator.remove();
        }

    }

    public static void findFreeTaxist(Iterable<User> users, Iterable<Taxidriver> taxidrivers){ //доделать и проверить
        Iterator<User> iterator = users.iterator();


       // System.out.println(users);
        //System.out.println(taxidrivers);
        while(iterator.hasNext()){
            User curUser = iterator.next();

            //if (curUser.getRoles().contains(Role.ADMIN)) System.out.println("admin");
            if(!curUser.getRoles().contains(Role.TAXIDRIVER)) {
                iterator.remove();
                //System.out.println(curUser.getRoles() + " remove");
                continue;
            }

            Iterator<Taxidriver> iteratorTaxi = taxidrivers.iterator();
            while (iteratorTaxi.hasNext()){

                Taxidriver taxidriver = iteratorTaxi.next();
                if(taxidriver.getUser().getId().equals(curUser.getId())){
                    iterator.remove();
                    break;
                }

            }


        }
       // System.out.println(users);
    }
}