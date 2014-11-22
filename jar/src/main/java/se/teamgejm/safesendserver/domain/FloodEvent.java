package se.teamgejm.safesendserver.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Emil Stjerneman
 */
@Entity
@Table(name = "flood")
@NamedQueries({
        @NamedQuery(name = "FloodEvent.purgeExpired", query = "DELETE FROM FloodEvent f WHERE f.expiration < :dateNow"),
        @NamedQuery(name = "FloodEvent.purge", query = "DELETE FROM FloodEvent f WHERE f.floodType = :event AND f.identifier = :identifier"),
        @NamedQuery(name = "FloodEvent.isAllowed", query = "SELECT COUNT(f) FROM FloodEvent f WHERE f.floodType = :event AND f.identifier = :identifier")
})
public class FloodEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "event")
    private FloodType floodType;

    @NotNull
    private String identifier;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime timestamp;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime expiration;

    public FloodEvent () {
        // Default constructor.
    }

    public FloodEvent (final FloodType floodType, final String identifier, final DateTime timestamp, final DateTime expiration) {
        this.floodType = floodType;
        this.identifier = identifier;
        this.timestamp = timestamp;
        this.expiration = expiration;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FloodEvent that = (FloodEvent) o;

        if (!expiration.equals(that.expiration)) {
            return false;
        }
        if (floodType != that.floodType) {
            return false;
        }
        if (!identifier.equals(that.identifier)) {
            return false;
        }
        if (!timestamp.equals(that.timestamp)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode () {
        int result = floodType.hashCode();
        result = 31 * result + identifier.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + expiration.hashCode();
        return result;
    }

    @Override
    public String toString () {
        return "FloodEvent{" +
                "id=" + id +
                ", floodType=" + floodType +
                ", identifier='" + identifier + '\'' +
                ", timestamp=" + timestamp +
                ", expiration=" + expiration +
                '}';
    }
}
