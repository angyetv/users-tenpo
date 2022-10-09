package cl.challenge.tenpo.controllers;

import cl.challenge.tenpo.cron.ExternalServicePercentage;
import cl.challenge.tenpo.dtos.PagedResponse;
import cl.challenge.tenpo.dtos.RegistrationOutDTO;
import cl.challenge.tenpo.dtos.mapper.RegistrationMapper;
import cl.challenge.tenpo.entities.RegistrationRequest;
import cl.challenge.tenpo.services.RegistrationRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/business")
public class BusinessController {

    private final RegistrationRequestService registrationService;

    private final ExternalServicePercentage externalServicePercentage;

    @GetMapping("/records")
    public ResponseEntity<PagedResponse<RegistrationOutDTO>> getRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<RegistrationRequest> records = registrationService.findAllRegistrations(PageRequest.of(page, size));
        return ResponseEntity.ok(new PagedResponse<>(
                RegistrationMapper.INSTANCE.mapToRegistrationOutDTOList(records.getContent()),
                records.getTotalPages(), records.getSize(), records.getTotalElements(), records.getNumber()));
    }

    @GetMapping("/percentage")
    public ResponseEntity<Float> addTwoNumbers(@RequestParam int numberOne, @RequestParam int numberTwo) {
        return ResponseEntity.ok(externalServicePercentage.calculate(numberOne, numberTwo));
    }
}
