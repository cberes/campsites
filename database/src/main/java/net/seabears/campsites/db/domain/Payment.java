package net.seabears.campsites.db.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "transaction_id", length = 50, nullable = false)
    private String transactionId;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal tax;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal fee;

    @OneToOne(mappedBy = "payment")
    private Reservation reservation;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(final BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(final BigDecimal fee) {
        this.fee = fee;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(final Reservation reservation) {
        this.reservation = reservation;
    }
}
