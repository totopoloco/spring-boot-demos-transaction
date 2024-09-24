package at.mavila.transaction.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {
  public void sendSms(String phoneNumber, String message) {
    log.info("SMS sent to {}: {}", phoneNumber, message);
  }
}
