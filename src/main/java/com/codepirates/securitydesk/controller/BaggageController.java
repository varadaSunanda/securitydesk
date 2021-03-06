package com.codepirates.securitydesk.controller;

import com.codepirates.securitydesk.model.Baggage;
import com.codepirates.securitydesk.model.Employee;
import com.codepirates.securitydesk.model.Token;
import com.codepirates.securitydesk.repository.BaggageRepository;
import com.codepirates.securitydesk.util.CommonFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaggageController {

    @Autowired
    private BaggageRepository baggageRepository;

    @Autowired
    private CommonFunctions commonFunctions;

    @RequestMapping(value = "/baggage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Baggage> checkinBaggage(@RequestBody Baggage baggage) throws Exception {
        if (baggageRepository.checkIfBaggageAlreadyExistsForEmployee(baggage.getEmployeeId()))
            throw new Exception("Baggage already exists for employee");
        baggage.setCheckinTime(commonFunctions.currentDateAndTime());
        baggageRepository.addNewBaggage(baggage);
        return baggageRepository.getAllCheckinedBaggages();
    }

    @RequestMapping(value = "/baggage/{employeeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Baggage> checkoutBaggage(@PathVariable String employeeId) {
        baggageRepository.updateBaggage(employeeId);
        return baggageRepository.getAllCheckinedBaggages();
    }

    @RequestMapping(value = "/baggage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Baggage> getAllCheckinedBaggages() {
        return baggageRepository.getAllCheckinedBaggages();
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Token> getAllUsableTokens() {
        return baggageRepository.getAllUsableTokens();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() { return  baggageRepository.getAllEmployees(); }
}
