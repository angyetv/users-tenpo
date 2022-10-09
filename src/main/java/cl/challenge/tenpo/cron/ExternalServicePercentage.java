package cl.challenge.tenpo.cron;

import cl.challenge.tenpo.exception.ExternalServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExternalServicePercentage {

    private int numberOne = -1;

    private int numberTwo;

    private float percentage = -1;

    @Retryable(value = {ExternalServiceException.class}, maxAttempts = 3, backoff = @Backoff(delay = 300))
    public Float calculate(int numberOne, int numberTwo) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        if (percentage == -1) {
            log.info("Reintentando...");
            throw new ExternalServiceException("Error al consumir el servicio externo");
        }
        log.info("percentage: {}", percentage);
        return numberOne + numberTwo + percentage;
    }

    @Scheduled(cron = "${cron.expression}")
    public void calculatePercentage() {
        if (numberOne == -1) {
            return;
        }
        float sum = numberOne + (float) numberTwo;
        percentage = (sum * (sum / 100));
        log.info("Change percentage to: {}", percentage);
    }
}
