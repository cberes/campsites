package net.seabears.campsites.db.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment",
        uniqueConstraints = @UniqueConstraint(columnNames = {"payment_processor_id", "transaction_id"}))
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_processor_id", nullable = false)
    private PaymentProcessor processor;

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

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public PaymentProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(final PaymentProcessor processor) {
        this.processor = processor;
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
