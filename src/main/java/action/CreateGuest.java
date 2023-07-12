package action;


import com.google.gson.Gson;
import dao.GuestDAO;
import dao.ReservationDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Guest;
import model.Reservation;
import util.EntityManagerUtil;
import util.GsonFactory;

import java.io.IOException;
import java.net.URLDecoder;
import java.time.LocalDate;

public class CreateGuest implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String cpf = req.getParameter("cpf")
                .replace(".", "")
                .replace("-", "");
        String birthDate = req.getParameter("birthDate");
        String phone = req.getParameter("phone")
                .replace("(", "")
                .replace(") ", "")
                .replace("-", "");

        String reservationEncoded = req.getParameter("reservationEncoded");
        String reservationJson = URLDecoder.decode(reservationEncoded, "UTF-8");
        Gson gson = GsonFactory.createGson();
        Reservation reservation = gson.fromJson(reservationJson, Reservation.class);

        Guest guest = new Guest(
                name,
                cpf,
                LocalDate.parse(birthDate),
                phone,
                reservation
        );

        req.setAttribute("guest", guest);
        req.setAttribute("reservation", reservation);

        EntityManager entityManager = EntityManagerUtil.create();
        GuestDAO guestDAO = new GuestDAO(entityManager);
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);

        entityManager.getTransaction().begin();

        reservation.setGuest(guest);
        reservationDAO.persist(reservation);
        guestDAO.persist(guest);

        entityManager.getTransaction().commit();
        entityManager.close();

        String title = "criado";

        req.setAttribute("title", title);
        return "forward:success.jsp";
    }
}
