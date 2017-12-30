package net.seabears.campsites.db.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "payment_processor")
public class PaymentProcessor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "boolean not null default TRUE")
    private boolean active = true;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "processor")
    private Set<Payment> payments;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(final Set<Payment> payments) {
        this.payments = payments;
    }
}
