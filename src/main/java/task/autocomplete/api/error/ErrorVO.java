package task.autocomplete.api.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorVO {
	
	private int code = 0;
	private String message = "Error";

}
