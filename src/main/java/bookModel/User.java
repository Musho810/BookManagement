package bookModel;

import bookEnum.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserType userType;


}
