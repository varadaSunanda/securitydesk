package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Baggage;
import com.codepirates.securitydesk.model.Token;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface BaggageRepository {

    Baggage addNewBaggage(Baggage baggage);

    UpdateResult updateBaggage(String employeeId);

    List<Baggage> getAllBaggages();

    List<Baggage> getAllCheckinedBaggages();

    List<Token> getAllUsableTokens();

    boolean checkIfBaggageAlreadyExistsForEmployee(String employeeId);
}
