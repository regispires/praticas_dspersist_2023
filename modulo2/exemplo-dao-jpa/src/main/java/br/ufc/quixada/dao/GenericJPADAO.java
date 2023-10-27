package br.ufc.quixada.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenericJPADAO<T> implements GenericDAO<T> {
    protected Class<T> persistentClass;

    public GenericJPADAO() {}

    public GenericJPADAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public void save(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            em.merge(entity);
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollback();
            throw new DAOException("ERRO: Não foi possível realizar a operação.", e);
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public void delete(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            em.remove(em.merge(entity));
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollback();
            throw new DAOException("ERRO: Não foi possível realizar a operação.", e);
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public T find(Object id) {
        return getEm().find(persistentClass, id);
    }

    public List<T> find() {
        CriteriaQuery<T> cq = getEm().getCriteriaBuilder().createQuery(persistentClass);
        cq.from(persistentClass);
        return getEm().createQuery(cq).getResultList();
    }

    public EntityManager getEm() {
        return JPAUtil.getEntityManager();
    }

    public void beginTransaction() {
        JPAUtil.beginTransaction();
    }

    public void commit() {
        JPAUtil.commit();
    }

    public void rollback() {
        JPAUtil.rollback();
    }

    public void close() {
        JPAUtil.closeEntityManager();
    }

}
