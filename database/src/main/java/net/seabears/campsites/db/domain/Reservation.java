package net.seabears.campsites.db.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "campsite_id", nullable = false)
    private Campsite campsite;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(nullable = false)
    private LocalDate starting;

    @Column(nullable = false)
    private LocalDate ending;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public Campsite getCampsite() {
        return campsite;
    }

    public void setCampsite(final Campsite campsite) {
        this.campsite = campsite;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(final Payment payment) {
        this.payment = payment;
    }

    public LocalDate getStarting() {
        return starting;
    }

    public void setStarting(final LocalDate starting) {
        this.starting = starting;
    }

    public LocalDate getEnding() {
        return ending;
    }

    public void setEnding(final LocalDate ending) {
        this.ending = ending;
    }
}
