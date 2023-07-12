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

public class Update implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String registry = req.getParameter("registry");
        String[] redirection = registry.split(":");

        EntityManager entityManager = EntityManagerUtil.create();
        GuestDAO guestDAO = new GuestDAO(entityManager);
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);
        Long id = Long.valueOf(redirection[1]);

        if (redirection[0].equals("reservation")) {
            Reservation reservation = reservationDAO.find(id);
            req.setAttribute("reservation", reservation);
            return "forward:update_reservation.jsp";

        } else {
            Guest guest = guestDAO.find(id);

            if (guest.getName().equals("Duquiduqui Silva e Silva")){
                return "forward:duquiduqui.jsp";
            }

            req.setAttribute("guest", guest);
            return "forward:update_guest.jsp";
        }
    }
}
