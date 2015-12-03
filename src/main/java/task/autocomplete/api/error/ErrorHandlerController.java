package task.autocomplete.api.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler
	@ResponseBody
	public ErrorVO handleException(Exception exception, HttpServletRequest request, HttpServletResponse response){
		ErrorVO errorVO = new ErrorVO();
		errorVO.setCode(-1);
		errorVO.setMessage(exception.getMessage());
		return errorVO;
	}
	
}
