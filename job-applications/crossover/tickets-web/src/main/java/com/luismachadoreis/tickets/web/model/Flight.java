package com.luismachadoreis.tickets.web.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

/**
 * @author Luis Machado Reis
 */
@Entity
@Table(name = "flights")
public class Flight extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="id_route", nullable=false)
    private Route route;

    @Column(name = "boarding_terminal", length = 10, nullable = false)
    private String boardingTerminal;

    @Column(name = "gate", length = 10, nullable = false)
    private String boardingGate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private FlightStatus status;

    @Column(name = "checkin_date", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime checkin;

    @Column(name = "boarding_date", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime boarding;

    @Column(name = "takeoff_date", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime takeOff;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="id_flight")
    private List<Ticket> tickets;

    @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="id_staff", nullable=true)
    private User staff;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getBoardingTerminal() {
        return boardingTerminal;
    }

    public void setBoardingTerminal(String boardingTerminal) {
        this.boardingTerminal = boardingTerminal;
    }

    public String getBoardingGate() {
        return boardingGate;
    }

    public void setBoardingGate(String boardingGate) {
        this.boardingGate = boardingGate;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public DateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(DateTime checkin) {
        this.checkin = checkin;
    }

    public DateTime getBoarding() {
        return boarding;
    }

    public void setBoarding(DateTime boarding) {
        this.boarding = boarding;
    }

    public DateTime getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(DateTime takeOff) {
        this.takeOff = takeOff;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (!id.equals(flight.id)) return false;
        if (!route.equals(flight.route)) return false;
        if (!boardingTerminal.equals(flight.boardingTerminal)) return false;
        if (!boardingGate.equals(flight.boardingGate)) return false;
        if (status != flight.status) return false;
        if (!checkin.equals(flight.checkin)) return false;
        if (!boarding.equals(flight.boarding)) return false;
        if (!takeOff.equals(flight.takeOff)) return false;
        if (tickets != null ? !tickets.equals(flight.tickets) : flight.tickets != null) return false;
        return staff != null ? staff.equals(flight.staff) : flight.staff == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + route.hashCode();
        result = 31 * result + boardingTerminal.hashCode();
        result = 31 * result + boardingGate.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + checkin.hashCode();
        result = 31 * result + boarding.hashCode();
        result = 31 * result + takeOff.hashCode();
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", route=" + route +
                ", boardingTerminal='" + boardingTerminal + '\'' +
                ", boardingGate='" + boardingGate + '\'' +
                ", status=" + status +
                ", checkin=" + checkin +
                ", boarding=" + boarding +
                ", takeOff=" + takeOff +
                ", tickets=" + tickets +
                ", staff=" + staff +
                '}';
    }
}
