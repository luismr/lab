package com.luismachadoreis.tickets.web.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author Luis Machado Reis
 */
@Entity
@Table(name = "tickets")
public class Ticket extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="id_user", nullable=false)
    private User user;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Flight flight;

    @Column(name = "payment_date", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime paymentDate;

    @Column(name = "cancellation_date", nullable = true)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime cancelationDate;

    @Column(name = "notification_date", nullable = true)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime notificationDate;

    @Column(name = "price", nullable = true)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private TicketStatus status;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public DateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(DateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public DateTime getCancelationDate() {
        return cancelationDate;
    }

    public void setCancelationDate(DateTime cancelationDate) {
        this.cancelationDate = cancelationDate;
    }

    public DateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(DateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (!id.equals(ticket.id)) return false;
        if (!user.equals(ticket.user)) return false;
        if (!flight.equals(ticket.flight)) return false;
        if (paymentDate != null ? !paymentDate.equals(ticket.paymentDate) : ticket.paymentDate != null) return false;
        if (cancelationDate != null ? !cancelationDate.equals(ticket.cancelationDate) : ticket.cancelationDate != null)
            return false;
        if (notificationDate != null ? !notificationDate.equals(ticket.notificationDate) : ticket.notificationDate != null)
            return false;
        if (!price.equals(ticket.price)) return false;
        return status == ticket.status;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + flight.hashCode();
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (cancelationDate != null ? cancelationDate.hashCode() : 0);
        result = 31 * result + (notificationDate != null ? notificationDate.hashCode() : 0);
        result = 31 * result + price.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", flight=" + flight +
                ", paymentDate=" + paymentDate +
                ", cancelationDate=" + cancelationDate +
                ", notificationDate=" + notificationDate +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
