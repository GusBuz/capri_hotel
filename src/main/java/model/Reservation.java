package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @FutureOrPresent
    @Column(name = "checkin_date")
    private LocalDate checkinDate;
    @NotNull
    @Future
    @Column(name = "checkout_date")
    private LocalDate checkoutDate;
    @NotNull
    @DecimalMin(value = "49.70")
    @Column(name = "reservation_cost")
    private BigDecimal cost;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    public Reservation(LocalDate checkinDate, LocalDate checkoutDate, BigDecimal cost, PaymentMethod paymentMethod) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.cost = cost;
        this.paymentMethod = paymentMethod;
    }

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate dataEntrada) {
        this.checkinDate = dataEntrada;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate dataSaida) {
        this.checkoutDate = dataSaida;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal valor) {
        this.cost = valor;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
