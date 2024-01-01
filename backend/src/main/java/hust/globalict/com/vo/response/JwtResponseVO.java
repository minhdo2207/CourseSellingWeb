package hust.globalict.com.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseVO {

    private String token;

    private Long id;

    private String username;

    private String email;

    private List<String> roles;

}
