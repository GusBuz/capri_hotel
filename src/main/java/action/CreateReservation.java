package action;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PaymentMethod;
import model.Reservation;
import util.GsonFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;

public class CreateReservation implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String checkinDate = req.getParameter("checkinDate");
        String checkoutDate = req.getParameter("checkoutDate");
        String reservationCost = req.getParameter("reservationCost")
                .replace("R$ ", "")
                .replace(",", "")
                .replace(".", "");
        String paymentMethod = req.getParameter("paymentMethod");

        StringBuilder sb = new StringBuilder(reservationCost);
        sb.insert(sb.length() - 2, ".");
        String formattedValue = sb.toString();

        Reservation reservation = new Reservation(
                LocalDate.parse(checkinDate),
                LocalDate.parse(checkoutDate),
                new BigDecimal(formattedValue),
                PaymentMethod.valueOf(paymentMethod)
        );

        Gson gson = GsonFactory.createGson();
        String reservationJson = gson.toJson(reservation, Reservation.class);
        String reservationEncoded = URLEncoder.encode(reservationJson, "UTF-8");

        req.setAttribute("reservationEncoded", reservationEncoded);
        return "forward:guests_form.jsp";
    }
}
