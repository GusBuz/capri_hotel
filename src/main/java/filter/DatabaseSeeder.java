package filter;

import dao.GuestDAO;
import dao.ReservationDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Guest;
import model.PaymentMethod;
import model.Reservation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import util.EntityManagerUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "DatabaseSeeder")
public class DatabaseSeeder implements Filter {
    private FilterConfig config;
    private boolean initialized = false;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
    public void destroy() {}
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (!initialized) {
            ServletContext context = config.getServletContext();

            String reservationFilePath = context.getRealPath("/WEB-INF/db_seeds/reservations.csv");
            String guestFilePath = context.getRealPath("/WEB-INF/db_seeds/guests.csv");
            File reservationCsv = new File(reservationFilePath);
            File guestCsv = new File(guestFilePath);

            List<Reservation> reservationList = new ArrayList<>();
            List<Guest> guestList = new ArrayList<>();

            try (Reader reservationReader = new FileReader(reservationCsv);
                 Reader guestReader = new FileReader(guestCsv)) {

                CSVParser reservationParser = new CSVParser(reservationReader, CSVFormat.DEFAULT);
                CSVParser guestParser = new CSVParser(guestReader, CSVFormat.DEFAULT);

                for (CSVRecord reservationRecord : reservationParser) {
                    String checkinDate = reservationRecord.get(0);
                    String checkoutDate = reservationRecord.get(1);
                    String reservationCost = reservationRecord.get(2);
                    String paymentMethod = reservationRecord.get(3);

                    Reservation reservation = new Reservation(
                            LocalDate.parse(checkinDate),
                            LocalDate.parse(checkoutDate),
                            new BigDecimal(reservationCost),
                            PaymentMethod.valueOf(paymentMethod)
                    );

                    for (CSVRecord guestRecord : guestParser) {
                        String name = guestRecord.get(0);
                        String cpf = guestRecord.get(1);
                        String birthDate = guestRecord.get(2);
                        String phone = guestRecord.get(3);

                        Guest guest = new Guest(
                                name,
                                cpf,
                                LocalDate.parse(birthDate),
                                phone,
                                reservation
                        );
                        guestList.add(guest);
                    }
                    reservationList.add(reservation);

                }
                EntityManager entityManager = EntityManagerUtil.create();

                GuestDAO guestDAO = new GuestDAO(entityManager);
                ReservationDAO reservationDAO = new ReservationDAO(entityManager);

                entityManager.getTransaction().begin();

                for (int i = 0; i < reservationList.size(); i++) {
                    if (i < guestList.size()) {
                        Guest guest = guestList.get(i);
                        Reservation reservation = reservationList.get(i);
                        reservation.setGuest(guest);
                        reservationDAO.persist(reservation);
                        guestDAO.persist(guest);
                    }
                }
                entityManager.getTransaction().commit();
                entityManager.close();

            }
            initialized = true;
        }
        chain.doFilter(req, resp);
    }
}
