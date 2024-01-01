package hust.globalict.com.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;
}