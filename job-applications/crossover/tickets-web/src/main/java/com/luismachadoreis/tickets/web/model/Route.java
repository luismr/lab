package com.luismachadoreis.tickets.web.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @author Luis Machado Reis
 */
@Entity
@Table(name = "routes")
public class Route extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="id_aircraft", nullable=false)
    private Aircraft aircraft;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "boarding_terminal", length = 10, nullable = false)
    private String boardingTerminal;

    @Column(name = "gate", length = 10, nullable = false)
    private String boardingGate;

    @Column(name = "origin", length = 100, nullable = false)
    private String origin;

    @Column(name = "origin_airport", length = 10, nullable = false)
    private String originAirport;

    @Column(name = "destination", length = 100, nullable = false)
    private String destination;

    @Column(name = "destination_airport", length = 10, nullable = false)
    private String destinationAirport;

    @Column(name = "weekdays", length = 50, nullable = false)
    private String weekdays;

    @Column(name = "time_to_checkin", length = 8, nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentTimeOfDayAsString")
    private String timeToCheckin;

    @Column(name = "time_to_board", length = 8, nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentTimeOfDayAsString")
    private String timeToBoard;

    @Column(name = "time_to_departure", length = 8, nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentTimeOfDayAsString")
    private String timeToDeparture;

    @Column(name = "price_per_seat", nullable = false)
    private Double pricePerSeat;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(String weekdays) {
        this.weekdays = weekdays;
    }

    public String getTimeToCheckin() {
        return timeToCheckin;
    }

    public void setTimeToCheckin(String timeToCheckin) {
        this.timeToCheckin = timeToCheckin;
    }

    public String getTimeToBoard() {
        return timeToBoard;
    }

    public void setTimeToBoard(String timeToBoard) {
        this.timeToBoard = timeToBoard;
    }

    public String getTimeToDeparture() {
        return timeToDeparture;
    }

    public void setTimeToDeparture(String timeToDeparture) {
        this.timeToDeparture = timeToDeparture;
    }

    public Double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(Double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (!id.equals(route.id)) return false;
        if (!aircraft.equals(route.aircraft)) return false;
        if (!name.equals(route.name)) return false;
        if (!boardingTerminal.equals(route.boardingTerminal)) return false;
        if (!boardingGate.equals(route.boardingGate)) return false;
        if (!origin.equals(route.origin)) return false;
        if (!originAirport.equals(route.originAirport)) return false;
        if (!destination.equals(route.destination)) return false;
        if (!destinationAirport.equals(route.destinationAirport)) return false;
        if (!weekdays.equals(route.weekdays)) return false;
        if (!timeToCheckin.equals(route.timeToCheckin)) return false;
        if (!timeToBoard.equals(route.timeToBoard)) return false;
        if (!timeToDeparture.equals(route.timeToDeparture)) return false;
        return pricePerSeat.equals(route.pricePerSeat);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + aircraft.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + boardingTerminal.hashCode();
        result = 31 * result + boardingGate.hashCode();
        result = 31 * result + origin.hashCode();
        result = 31 * result + originAirport.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + destinationAirport.hashCode();
        result = 31 * result + weekdays.hashCode();
        result = 31 * result + timeToCheckin.hashCode();
        result = 31 * result + timeToBoard.hashCode();
        result = 31 * result + timeToDeparture.hashCode();
        result = 31 * result + pricePerSeat.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", aircraft=" + aircraft +
                ", name='" + name + '\'' +
                ", boardingTerminal='" + boardingTerminal + '\'' +
                ", boardingGate='" + boardingGate + '\'' +
                ", origin='" + origin + '\'' +
                ", originAirport='" + originAirport + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationAirport='" + destinationAirport + '\'' +
                ", weekdays='" + weekdays + '\'' +
                ", timeToCheckin='" + timeToCheckin + '\'' +
                ", timeToBoard='" + timeToBoard + '\'' +
                ", timeToDeparture='" + timeToDeparture + '\'' +
                ", pricePerSeat=" + pricePerSeat +
                '}';
    }
}
