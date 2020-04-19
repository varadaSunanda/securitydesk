package com.codepirates.securitydesk.Repository;

import com.codepirates.securitydesk.Model.Baggage;
import com.codepirates.securitydesk.Model.Token;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface BaggageRepository {

    Baggage addNewBaggage(Baggage baggage);

    UpdateResult updateBaggage(String employeeId);

    List<Baggage> getAllBaggages();

    List<Baggage> getAllCheckinedBaggages();

    List<Token> getAllUsableTokens();
}
