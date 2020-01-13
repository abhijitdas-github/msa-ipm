package abhijit.das.io.health;

import javax.inject.Inject;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import abhijit.das.io.JustGifItProperties;

@Component
public class JustGifItHealthIndicator implements HealthIndicator{

	@Inject
	private JustGifItProperties properties;
	
	@Override
	public Health health() {
		if(!properties.getGifLocation().canWrite()) {
			Health.down().build();
		}
		return Health.up().withDetail("map key", "map.vale").build();
	}

}
