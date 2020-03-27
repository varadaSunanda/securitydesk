package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Baggage;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface BaggageRepository {

    Baggage addNewBaggage(Baggage baggage);

    UpdateResult updateBaggage(String employeeId);

    List<Baggage> getAllBaggages();

    List<Baggage> getAllCheckinedBaggages();
}
