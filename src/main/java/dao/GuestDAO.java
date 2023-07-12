package dao;

import jakarta.persistence.EntityManager;
import model.Guest;

import java.util.List;

public class GuestDAO {

    private final EntityManager entityManager;

    public GuestDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(Guest guest) {
        this.entityManager.persist(guest);
    }

    public Guest find(Long id) {
        return this.entityManager.find(Guest.class, id);
    }

    public void remove(Guest guest) {
        this.entityManager.remove(guest);
    }

    public List<Guest> findByName(String name) {
        String jpql = "SELECT g FROM Guest g WHERE LOWER(g.name) LIKE (?1)";
        return entityManager.createQuery(jpql, Guest.class).setParameter(1, "%" + name.toLowerCase() + "%").getResultList();
    }
    public List<Guest> findByCpf(String cpf) {
        String jpql = "SELECT g FROM Guest g WHERE g.cpf = ?1";
        return entityManager.createQuery(jpql, Guest.class).setParameter(1, cpf).getResultList();
    }
    public List<Guest> findAll() {
        String jpql = "SELECT h FROM Guest h";
        return entityManager.createQuery(jpql, Guest.class).getResultList();
    }
}
