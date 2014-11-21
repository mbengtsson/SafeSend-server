package se.teamgejm.safesendserver.rest.model.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Emil Stjerneman
 */
public class ValidateCredentialsRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }
}
