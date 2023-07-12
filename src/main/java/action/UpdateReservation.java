package action;

import dao.GuestDAO;
import dao.ReservationDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PaymentMethod;
import model.Guest;
import model.Reservation;
import util.EntityManagerUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateReservation implements Action{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramId = req.getParameter("reservationId");
        Long id = Long.valueOf(paramId);

        LocalDate checkinDate = LocalDate.parse(req.getParameter("checkinDate"));
        LocalDate checkoutDate = LocalDate.parse(req.getParameter("checkoutDate"));

        String cost = req.getParameter("reservationCost")
                .replace("R$ ", "")
                .replace(",", "")
                .replace(".", "");
        StringBuilder sb = new StringBuilder(cost);
        sb.insert(sb.length() - 2, ".");
        String formattedCost = sb.toString();

        String paymentMethod = req.getParameter("paymentMethod");

        EntityManager entityManager = EntityManagerUtil.create();
        GuestDAO guestDAO = new GuestDAO(entityManager);
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);

        entityManager.getTransaction().begin();

        Reservation reservation = reservationDAO.find(id);
        Guest guest = guestDAO.find(id);

        reservation.setCheckinDate(checkinDate);
        reservation.setCheckoutDate(checkoutDate);
        reservation.setCost(new BigDecimal(formattedCost));
        reservation.setPaymentMethod(PaymentMethod.valueOf(paymentMethod));

        entityManager.getTransaction().commit();
        entityManager.close();

        String title = "alterado";

        req.setAttribute("title", title);
        req.setAttribute("guest", guest);
        req.setAttribute("reservation", reservation);

        return "forward:success.jsp";
    }
}
