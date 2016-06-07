/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import skogemann.entity.Address;
import skogemann.entity.CityInfo;
import skogemann.entity.Hobby;

/**
 *
 * @author thomas
 */
public class HobbyService {

    public Hobby findHobby(EntityManager em, String name) {

        try {
            return em.createQuery("SELECT a FROM Hobby a "
                    + "WHERE a.name = :name", Hobby.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public int getCountOfSpecificHobby(EntityManagerFactory emf, String name) {
        EntityManager em = emf.createEntityManager();
        int res = -1;
        try {
            Hobby temp = new Hobby();
            temp = em.createQuery("SELECT h FROM Hobby h "
                    + "WHERE h.name = :name", Hobby.class)
                    .setParameter("name", name)
                    .getSingleResult();
            res = temp.getPersons().size();
        } catch (NoResultException e) {
        }
        return res;
    }

}
