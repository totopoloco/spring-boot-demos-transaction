package at.mavila.transaction.configuration;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

  @Value("${spring.application.name}")
  private String applicationName;

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(this.applicationName);
  }
}