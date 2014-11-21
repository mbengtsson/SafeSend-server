package se.teamgejm.safesendserver.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * @author Emil Stjerneman
 */
@Entity
public class Flood {

    @NotNull
    @Enumerated(EnumType.STRING)
    private FloodEvent floodEvent;

    @NotNull
    private String identifier;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime timestamp;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime expiration;

    public Flood () {

    }

    public Flood (FloodEvent floodEvent, String identifier, DateTime timestamp, DateTime expiration) {
        this.floodEvent = floodEvent;
        this.identifier = identifier;
        this.timestamp = timestamp;
        this.expiration = expiration;
    }

    public FloodEvent getFloodEvent () {
        return floodEvent;
    }

    public void setFloodEvent (FloodEvent floodEvent) {
        this.floodEvent = floodEvent;
    }

    public String getIdentifier () {
        return identifier;
    }

    public void setIdentifier (String identifier) {
        this.identifier = identifier;
    }

    public DateTime getTimestamp () {
        return timestamp;
    }

    public void setTimestamp (DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public DateTime getExpiration () {
        return expiration;
    }

    public void setExpiration (DateTime expiration) {
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

        Flood flood = (Flood) o;

        if (!expiration.equals(flood.expiration)) {
            return false;
        }
        if (floodEvent != flood.floodEvent) {
            return false;
        }
        if (!identifier.equals(flood.identifier)) {
            return false;
        }
        if (!timestamp.equals(flood.timestamp)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode () {
        int result = floodEvent.hashCode();
        result = 31 * result + identifier.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + expiration.hashCode();
        return result;
    }

    public enum FloodEvent {
        FAILED_VALIDATE_CREDENTIALS,
    }
}
