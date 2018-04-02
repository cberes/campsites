package net.seabears.campsites.db.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(final long id) {
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

    /**
     * Returns the date when the reservation begins.
     * @return start date
     */
    public LocalDate getStarting() {
        return starting;
    }

    /**
     * Sets the date when the reservation begins.
     * @param starting start date
     */
    public void setStarting(final LocalDate starting) {
        this.starting = starting;
    }

    /**
     * Returns the date when the reservation ends.
     * Typically campsites are reserved for a number of nights. Thus the reservation may start 14:00 Monday
     * and end 12:00 Tuesday. In this case, the ending date will be Tuesday.
     * This allows consumers to get the total number of nights by subtracting
     * {@link #getStarting() the starting date} from this ending date.
     * @return end date
     */
    public LocalDate getEnding() {
        return ending;
    }

    /**
     * Sets the date when the reservation ends.
     * @param ending end date
     * @see #getEnding()
     */
    public void setEnding(final LocalDate ending) {
        this.ending = ending;
    }

    /**
     * Returns the number of nights for which this record reserves the campsite.
     * @return number of nights reserved
     */
    public int getNights() {
        return (int) ChronoUnit.DAYS.between(starting, ending);
    }
}
