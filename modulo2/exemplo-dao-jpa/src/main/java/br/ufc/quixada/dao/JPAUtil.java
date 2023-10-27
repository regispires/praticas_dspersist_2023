package br.ufc.quixada.dao;

import java.util.Properties;
import jakarta.persistence.*;

import br.ufc.quixada.Config;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JPAUtil {
    private static final EntityManagerFactory emf;

    private static ThreadLocal<EntityManager> ems = new ThreadLocal<EntityManager>();

    static {
		Properties props = Config.getConfig();
        String persistenceUnit = props.getProperty("persistence.unit");
		log.info("persistence.unit: {}", persistenceUnit);
        emf = Persistence.createEntityManagerFactory(persistenceUnit);
    }

    /**
     * Obtém o EntityManager vinculado à  Thread atual. Se não existir, é criado e vinculado à Thread atual.
     */
    public static EntityManager getEntityManager() {
        EntityManager em = ems.get();
        if (em == null) {
            em = emf.createEntityManager();
            ems.set(em);
        }
        return em;
    }

    /**
     *  Fecha o EntityManager atrelado à  Thread atual e retira-o da ThreadLocal.
     */
    public static void closeEntityManager() {
        EntityManager em = ems.get();
        if (em != null) {
            EntityTransaction tx = em.getTransaction();
            if (tx.isActive()) {
                tx.commit();
            }
            em.close();
            ems.set(null);
        }
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void commit() {
        EntityTransaction tx = getEntityManager().getTransaction();
        if (tx.isActive()) {
            tx.commit();
        }
    }

    public static void rollback() {
        EntityTransaction tx = getEntityManager().getTransaction();
        if (tx.isActive()) {
            tx.rollback();
        }
    }
}
