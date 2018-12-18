package com.api.dse.poc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.dse.poc.services.*;
import com.api.dse.poc.model.*;
import javax.servlet.http.HttpServletRequest;


import com.api.dse.poc.model.AlertObject;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-18T13:04:59.632+05:30")

@Controller
public class CustomerreferencedatamanagementApiController implements CustomerreferencedatamanagementApi {
	@Autowired

	private HttpServletRequest request;

	@Autowired
	 private AlertsI alertsI;




    public ResponseEntity<AlertObject> alerts(@ApiParam(value = "ID of customer for which to return alerts",required=true ) @PathVariable("customerid") Long customerid) {

	request.setAttribute("operationId","alerts");
	AlertsResponseWrapper res = alertsI.execute(new AlertsP(customerid));
        return new ResponseEntity<AlertObject>(res.getResponse(),res.getHeaders(),HttpStatus.OK);
    }

}
