package ca.mcgill.ecse428.where2eat.backend.dto;

public class SystemUserDto {

    private String firstName;
    private String lastName;
    private LoginDto loginDto;


    @SuppressWarnings("unchecked")
    public SystemUserDto(String firstName, String lastName, String username, String password, LoginDto loginDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginDto = loginDto;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LoginDto getLoginDto() {
        return loginDto;
    }
}
