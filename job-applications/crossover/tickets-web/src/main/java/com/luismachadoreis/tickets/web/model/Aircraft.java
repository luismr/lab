package com.luismachadoreis.tickets.web.model;

import javax.persistence.*;

/**
 * @author Luis Machado Reis
 */
@Entity
@Table(name = "aircrafts")
public class Aircraft extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "model", length = 50, nullable = false)
    private String model;

    @Column(name = "seat_rows", length = 2, nullable = false)
    private int seatRows;

    @Column(name = "seat_cols", length = 2, nullable = false)
    private int seatColumns;

    @Column(name = "seat_corridors", length = 10, nullable = false)
    private String seatCorridors;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(int seatRows) {
        this.seatRows = seatRows;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(int seatColumns) {
        this.seatColumns = seatColumns;
    }

    public String getSeatCorridors() {
        return seatCorridors;
    }

    public void setSeatCorridors(String seatCorridors) {
        this.seatCorridors = seatCorridors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft)) return false;

        Aircraft aircraft = (Aircraft) o;

        if (seatRows != aircraft.seatRows) return false;
        if (seatColumns != aircraft.seatColumns) return false;
        if (!id.equals(aircraft.id)) return false;
        if (!name.equals(aircraft.name)) return false;
        if (!model.equals(aircraft.model)) return false;
        return seatCorridors.equals(aircraft.seatCorridors);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + seatRows;
        result = 31 * result + seatColumns;
        result = 31 * result + seatCorridors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", seatRows=" + seatRows +
                ", seatColumns=" + seatColumns +
                ", seatCorridors='" + seatCorridors + '\'' +
                '}';
    }
}
