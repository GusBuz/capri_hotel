package action;

import dao.GuestDAO;
import dao.ReservationDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Guest;
import model.Reservation;
import util.EntityManagerUtil;

import java.io.IOException;

public class DeleteRegistry implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reservationParamId = req.getParameter("reservation");
        String guestParamId = req.getParameter("guest");

        EntityManager entityManager = EntityManagerUtil.create();
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);
        GuestDAO guestDAO = new GuestDAO(entityManager);

        if (!reservationParamId.isEmpty()) {
            Long reservationId = Long.valueOf(reservationParamId);
            Reservation reservation = reservationDAO.find(reservationId);

            entityManager.getTransaction().begin();
            reservationDAO.remove(reservation);
            entityManager.getTransaction().commit();
            entityManager.close();

            return "redirect:index?action=Menu";
        } else if (!guestParamId.isEmpty()) {
            Long guestId = Long.valueOf(guestParamId);
            Guest guest = guestDAO.find(guestId);

            entityManager.getTransaction().begin();
            guestDAO.remove(guest);
            entityManager.getTransaction().commit();
            entityManager.close();

            return "redirect:index?action=Menu";
        } else {
            throw new ServletException();
        }
    }
}
