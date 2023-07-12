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
import java.time.LocalDate;

public class UpdateGuest implements Action{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramId = req.getParameter("guestId");
        Long id = Long.valueOf(paramId);
        String name = req.getParameter("name");
        String cpf = req.getParameter("cpf")
                .replace(".", "")
                .replace("-", "");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));
        String phone = req.getParameter("phone")
                .replace("(", "")
                .replace(") ", "")
                .replace("-", "");

        EntityManager entityManager = EntityManagerUtil.create();
        GuestDAO guestDAO = new GuestDAO(entityManager);
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);

        entityManager.getTransaction().begin();

        Guest guest = guestDAO.find(id);
        Reservation reservation = reservationDAO.find(guest.getReservation().getId());

        guest.setName(name);
        guest.setCpf(cpf);
        guest.setBirthDate(birthDate);
        guest.setPhone(phone);

        entityManager.getTransaction().commit();
        entityManager.close();

        String title = "alterado";

        req.setAttribute("title", title);
        req.setAttribute("guest", guest);
        req.setAttribute("reservation", reservation);

        return "forward:success.jsp";
    }
}
