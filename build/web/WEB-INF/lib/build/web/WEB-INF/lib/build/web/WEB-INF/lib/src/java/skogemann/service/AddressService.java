/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import skogemann.entity.Address;
import skogemann.entity.CityInfo;

/**
 *
 * @author thomas
 */
public class AddressService {

    public Address findAddress(EntityManager em, String street, String additionalInfo, CityInfo cityInfo) {
     

        try {
            return em.createQuery("SELECT a FROM Address a "
                    + "WHERE a.street = :street "
                    + "AND a.additionalInfo = :info "
                    + "AND a.cityInfo = :cityInfo", Address.class)
                    .setParameter("street", street)
                    .setParameter("info", additionalInfo).setParameter("cityInfo", cityInfo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
