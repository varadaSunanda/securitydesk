package com.codepirates.securitydesk.controller;

import com.codepirates.securitydesk.model.Visitor;
import com.codepirates.securitydesk.repository.VisitorDAL;
import com.codepirates.securitydesk.repository.VisitorRepository;
import com.codepirates.securitydesk.util.CommonFunctions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitorController {

    private final VisitorRepository visitorRepository;
    private final VisitorDAL visitorDAL;
    private final CommonFunctions commonFunctions;

    public VisitorController(VisitorRepository visitorRepository, VisitorDAL visitorDAL, CommonFunctions commonFunctions) {
        this.visitorRepository = visitorRepository;
        this.visitorDAL = visitorDAL;
        this.commonFunctions = commonFunctions;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addVisitor")
    public List<Visitor> saveVisitor(@RequestBody Visitor visitor) {
        visitor.setCheckInTime(commonFunctions.currentDateAndTime());
        visitorDAL.addVisitor(visitor);
        return getAllVisitor();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateVisitor/{tagId}")
    public List<Visitor> updateVisitor(@PathVariable String tagId) {
        visitorDAL.updateVisitor(tagId);
        return visitorRepository.findAllByCheckOutTimeIsNotNull();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllVisitor")
    public List<Visitor> getAllVisitor() {
        return visitorRepository.findAllByCheckOutTimeIsNotNull();
    }

}
