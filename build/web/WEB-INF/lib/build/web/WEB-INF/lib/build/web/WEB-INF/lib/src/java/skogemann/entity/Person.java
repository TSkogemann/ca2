/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author thomas
 */
@Entity
@DiscriminatorValue ("Person")
public class Person extends InfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @ManyToMany(mappedBy = "persons", cascade = CascadeType.PERSIST)
    private Collection<Hobby> hobbies;

    public Collection<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Collection<Hobby> Hobbies) {
        this.hobbies = Hobbies;
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


}
