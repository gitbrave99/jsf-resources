/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Cliente;
import model.Transaccion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import model.Cuenta;

/**
 *
 * @author georg
 */
public class CuentaJpaController implements Serializable {

    public CuentaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuenta cuenta) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cuenta.getTransaccionCollection() == null) {
            cuenta.setTransaccionCollection(new ArrayList<Transaccion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente codCliente = cuenta.getCodCliente();
            if (codCliente != null) {
                codCliente = em.getReference(codCliente.getClass(), codCliente.getCodCliente());
                cuenta.setCodCliente(codCliente);
            }
            Collection<Transaccion> attachedTransaccionCollection = new ArrayList<Transaccion>();
            for (Transaccion transaccionCollectionTransaccionToAttach : cuenta.getTransaccionCollection()) {
                transaccionCollectionTransaccionToAttach = em.getReference(transaccionCollectionTransaccionToAttach.getClass(), transaccionCollectionTransaccionToAttach.getIdTransaccion());
                attachedTransaccionCollection.add(transaccionCollectionTransaccionToAttach);
            }
            cuenta.setTransaccionCollection(attachedTransaccionCollection);
            em.persist(cuenta);
            if (codCliente != null) {
                codCliente.getCuentaCollection().add(cuenta);
                codCliente = em.merge(codCliente);
            }
            for (Transaccion transaccionCollectionTransaccion : cuenta.getTransaccionCollection()) {
                Cuenta oldNumeroCuentaOfTransaccionCollectionTransaccion = transaccionCollectionTransaccion.getNumeroCuenta();
                transaccionCollectionTransaccion.setNumeroCuenta(cuenta);
                transaccionCollectionTransaccion = em.merge(transaccionCollectionTransaccion);
                if (oldNumeroCuentaOfTransaccionCollectionTransaccion != null) {
                    oldNumeroCuentaOfTransaccionCollectionTransaccion.getTransaccionCollection().remove(transaccionCollectionTransaccion);
                    oldNumeroCuentaOfTransaccionCollectionTransaccion = em.merge(oldNumeroCuentaOfTransaccionCollectionTransaccion);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCuenta(cuenta.getNumeroCuenta()) != null) {
                throw new PreexistingEntityException("Cuenta " + cuenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuenta cuenta) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cuenta persistentCuenta = em.find(Cuenta.class, cuenta.getNumeroCuenta());
            Cliente codClienteOld = persistentCuenta.getCodCliente();
            Cliente codClienteNew = cuenta.getCodCliente();
            Collection<Transaccion> transaccionCollectionOld = persistentCuenta.getTransaccionCollection();
            Collection<Transaccion> transaccionCollectionNew = cuenta.getTransaccionCollection();
            if (codClienteNew != null) {
                codClienteNew = em.getReference(codClienteNew.getClass(), codClienteNew.getCodCliente());
                cuenta.setCodCliente(codClienteNew);
            }
            Collection<Transaccion> attachedTransaccionCollectionNew = new ArrayList<Transaccion>();
            for (Transaccion transaccionCollectionNewTransaccionToAttach : transaccionCollectionNew) {
                transaccionCollectionNewTransaccionToAttach = em.getReference(transaccionCollectionNewTransaccionToAttach.getClass(), transaccionCollectionNewTransaccionToAttach.getIdTransaccion());
                attachedTransaccionCollectionNew.add(transaccionCollectionNewTransaccionToAttach);
            }
            transaccionCollectionNew = attachedTransaccionCollectionNew;
            cuenta.setTransaccionCollection(transaccionCollectionNew);
            cuenta = em.merge(cuenta);
            if (codClienteOld != null && !codClienteOld.equals(codClienteNew)) {
                codClienteOld.getCuentaCollection().remove(cuenta);
                codClienteOld = em.merge(codClienteOld);
            }
            if (codClienteNew != null && !codClienteNew.equals(codClienteOld)) {
                codClienteNew.getCuentaCollection().add(cuenta);
                codClienteNew = em.merge(codClienteNew);
            }
            for (Transaccion transaccionCollectionOldTransaccion : transaccionCollectionOld) {
                if (!transaccionCollectionNew.contains(transaccionCollectionOldTransaccion)) {
                    transaccionCollectionOldTransaccion.setNumeroCuenta(null);
                    transaccionCollectionOldTransaccion = em.merge(transaccionCollectionOldTransaccion);
                }
            }
            for (Transaccion transaccionCollectionNewTransaccion : transaccionCollectionNew) {
                if (!transaccionCollectionOld.contains(transaccionCollectionNewTransaccion)) {
                    Cuenta oldNumeroCuentaOfTransaccionCollectionNewTransaccion = transaccionCollectionNewTransaccion.getNumeroCuenta();
                    transaccionCollectionNewTransaccion.setNumeroCuenta(cuenta);
                    transaccionCollectionNewTransaccion = em.merge(transaccionCollectionNewTransaccion);
                    if (oldNumeroCuentaOfTransaccionCollectionNewTransaccion != null && !oldNumeroCuentaOfTransaccionCollectionNewTransaccion.equals(cuenta)) {
                        oldNumeroCuentaOfTransaccionCollectionNewTransaccion.getTransaccionCollection().remove(transaccionCollectionNewTransaccion);
                        oldNumeroCuentaOfTransaccionCollectionNewTransaccion = em.merge(oldNumeroCuentaOfTransaccionCollectionNewTransaccion);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cuenta.getNumeroCuenta();
                if (findCuenta(id) == null) {
                    throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cuenta cuenta;
            try {
                cuenta = em.getReference(Cuenta.class, id);
                cuenta.getNumeroCuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.", enfe);
            }
            Cliente codCliente = cuenta.getCodCliente();
            if (codCliente != null) {
                codCliente.getCuentaCollection().remove(cuenta);
                codCliente = em.merge(codCliente);
            }
            Collection<Transaccion> transaccionCollection = cuenta.getTransaccionCollection();
            for (Transaccion transaccionCollectionTransaccion : transaccionCollection) {
                transaccionCollectionTransaccion.setNumeroCuenta(null);
                transaccionCollectionTransaccion = em.merge(transaccionCollectionTransaccion);
            }
            em.remove(cuenta);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuenta> findCuentaEntities() {
        return findCuentaEntities(true, -1, -1);
    }

    public List<Cuenta> findCuentaEntities(int maxResults, int firstResult) {
        return findCuentaEntities(false, maxResults, firstResult);
    }

    private List<Cuenta> findCuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuenta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cuenta findCuenta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuenta> rt = cq.from(Cuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
