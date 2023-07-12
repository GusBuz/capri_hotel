package dao;

import jakarta.persistence.EntityManager;
import model.Reservation;

import java.time.LocalDate;
import java.util.List;

public class ReservationDAO {

    private final EntityManager entityManager;

    public ReservationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(Reservation reservation) {
        this.entityManager.persist(reservation);
    }

    public Reservation find(Long id) {
        return this.entityManager.find(Reservation.class, id);
    }

    public void remove(Reservation reservation) {
        this.entityManager.remove(reservation);
    }

    public List<Reservation> findAll() {
        String jpql = "SELECT r FROM Reservation r";
        return entityManager.createQuery(jpql, Reservation.class).getResultList();
    }

    public List<Reservation> findByCheckin(LocalDate dataEntrada) {
        String jpql = "SELECT r FROM Reservation r WHERE r.checkinDate = ?1";
        return entityManager.createQuery(jpql, Reservation.class).setParameter(1, dataEntrada).getResultList();
    }
}
