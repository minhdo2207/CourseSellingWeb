package hust.globalict.com.vo.response;

import hust.globalict.com.utils.Translator;
import org.springframework.http.HttpStatus;


public class CommonResponse<T> {

	public CommonResponse() {
	}

	private T data = null;

	private String message = Translator.toLocale("msg_success");

	private int statusCode = HttpStatus.OK.value();

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
