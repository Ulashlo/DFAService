package com.hse.dfa.backend.model.admin_requests;

import com.hse.dfa.backend.model.user_info.User;
import com.hse.dfa.backend.util.converters.attribute.IssuerRequestStatusAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Optional;

import static java.time.OffsetDateTime.now;
import static java.util.Optional.ofNullable;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "issuer_request", schema ="admin_requests")
public class IssuerRequest {
    @Id
    @Getter
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_who_sent_id")
    private User userWhoSent;

    @Getter
    @Column(name = "date_created")
    private OffsetDateTime dateCreated;

    @Getter
    @Setter
    @Convert(converter = IssuerRequestStatusAttributeConverter.class)
    @Column(name = "status")
    private IssuerRequestStatus status;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "admin_who_answered_id")
    private User adminWhoAnswered;

    @Setter
    @Column(name = "date_answered")
    private OffsetDateTime dateAnswered;

    public IssuerRequest(User userWhoSent) {
        this.userWhoSent = userWhoSent;
        this.dateCreated = now();
        this.status = IssuerRequestStatus.CREATED;
    }

    public Optional<User> getAdminWhoAnswered() {
        return ofNullable(adminWhoAnswered);
    }

    public Optional<OffsetDateTime> getDateAnswered() {
        return ofNullable(dateAnswered);
    }
}
