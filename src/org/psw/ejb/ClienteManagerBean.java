package org.psw.ejb;

import org.psw.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "ClienteManagerEJB")
public class ClienteManagerBean {

    @PersistenceContext
    EntityManager em;

    public ClienteManagerBean() {
    }

    public List<Cliente> getClienti(){
        Query q = em.createNamedQuery("getClienti");
        return q.getResultList();
    }
}
