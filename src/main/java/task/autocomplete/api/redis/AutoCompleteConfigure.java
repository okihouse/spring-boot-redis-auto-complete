package task.autocomplete.api.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AutoCompleteConfigure {

	@Value(value = "${autocomplete.min}")
	private double min = 0;
	
	@Value(value = "${autocomplete.max}")
	private double max = 5;

	@Value(value = "${autocomplete.count}")
	private long count = 30;
	
	@Value(value = "${autocomplete.delemeter}")
	private final String delemeter = "§";

}
