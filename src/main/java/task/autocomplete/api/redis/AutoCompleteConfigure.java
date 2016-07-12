package task.autocomplete.api.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="autocomplete")
public class AutoCompleteConfigure {

	private double min = 0;
	
	private double max = 5;

	private long count = 30;
	
	private final String delimiter = "§";

}
