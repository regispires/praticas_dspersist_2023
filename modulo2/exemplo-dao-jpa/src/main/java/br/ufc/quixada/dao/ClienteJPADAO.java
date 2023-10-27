package br.ufc.quixada.dao;

import java.util.List;

import jakarta.persistence.EntityManager;

import br.ufc.quixada.entity.Cliente;

public class ClienteJPADAO extends GenericJPADAO<Cliente> implements ClienteDAO {

    public ClienteJPADAO() {
        super(Cliente.class);
    }

    public void delete(int id) {
        delete(new Cliente(id));
    }

    public Cliente find(int id) {
        return find(Integer.valueOf(id));
    }

    public Cliente findByCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        Cliente cliente = em.createQuery("select c from Cliente c where c.cpf = :cpf", 
            Cliente.class).setParameter("cpf", cpf).getSingleResult();
        JPAUtil.closeEntityManager();
        return cliente;
    }

    public List<Cliente> findByNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cliente> clientes = em.createQuery("select c from Cliente c where upper(c.nome) like upper(:nome)", 
            Cliente.class).setParameter("nome", "%" + nome + "%").getResultList();
        JPAUtil.closeEntityManager();
        return clientes;
    }
    
}
