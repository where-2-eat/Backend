package ca.mcgill.ecse428.where2eat.backend.dto;

public class LoginDto {

    private String username;
    private String password;


    @SuppressWarnings("unchecked")
    public LoginDto(String username, String password) {
        this.username= username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
