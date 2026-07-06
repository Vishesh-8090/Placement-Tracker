package com.vishesh.placementtracker.exception;

import com.vishesh.placementtracker.dto.request.CompanyRequest;

public class CompanyAlreadyExistsExecption extends RuntimeException{

    public CompanyAlreadyExistsExecption(String message){
        super(message);
    }
}
