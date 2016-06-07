/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import skogemann.entity.CityInfo;

/**
 *
 * @author thomas
 */
public class CityInfoService {

    public CityInfo findCity(EntityManager em, String city, int ZipCode) {

        try {
            return em.createQuery("SELECT a FROM CityInfo a "
                    + "WHERE a.city = :city "
                    + "AND a.zipCode = :zip", CityInfo.class)
                    .setParameter("city", city)
                    .setParameter("zip", ZipCode).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Integer> getZipCodeList(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Integer> res = new ArrayList<>();
        
        try {
            List<CityInfo> resultList = em.createQuery("SELECT a from CityInfo a"
                    , CityInfo.class)
                    .getResultList();
            for (CityInfo ci : resultList) {
                res.add(ci.getZipCode());
            }

        } catch (NoResultException e) {
        }
        return res;
    }
}
