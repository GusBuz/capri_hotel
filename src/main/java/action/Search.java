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
import java.util.ArrayList;
import java.util.List;

public class Search implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        String searchKey = req.getParameter("searchKey");
        String dateSearchKey = req.getParameter("dateSearchKey");
        String filterType = req.getParameter("filterType");

        EntityManager entityManager = EntityManagerUtil.create();
        GuestDAO guestDAO = new GuestDAO(entityManager);
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);

        List<Reservation> reservationList = new ArrayList<>();
        List<Guest> guestList = new ArrayList<>();

        if (filter == null) {
            filter = "notFilter";
        }

        if (filter.equals("doFilter")) {
            if (filterType.equals("name") && !searchKey.isEmpty()) {
                guestList = guestDAO.findByName(searchKey);
                for (Guest guest : guestList) {
                    reservationList.add(guest.getReservation());
                }
                req.setAttribute("reservationList", reservationList);
                req.setAttribute("guestList", guestList);
                return "forward:search.jsp";

            } else if (filterType.equals("cpf") && !searchKey.isEmpty()) {
                String cpfFormatted = searchKey.replace(".", "").replace("-", "");
                guestList = guestDAO.findByCpf(cpfFormatted);

                for (Guest guest : guestList) {
                    reservationList.add(guest.getReservation());
                }
                req.setAttribute("reservationList", reservationList);
                req.setAttribute("guestList", guestList);
                return "forward:search.jsp";

            } else if (filterType.equals("checkinDate") && !dateSearchKey.isEmpty()) {
                reservationList = reservationDAO.findByCheckin(LocalDate.parse(dateSearchKey));

                for (Reservation reservation : reservationList) {
                    guestList.add(reservation.getGuest());
                }
                req.setAttribute("reservationList", reservationList);
                req.setAttribute("guestList", guestList);
                return "forward:search.jsp";
            }
        }

        reservationList = reservationDAO.findAll();
        guestList = guestDAO.findAll();
        req.setAttribute("reservationList", reservationList);
        req.setAttribute("guestList", guestList);
        return "forward:search.jsp";
    }
}
