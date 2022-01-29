package com.SelimGezer.GraduationProject.Controller;

import com.SelimGezer.GraduationProject.Dtos.CreditDetailResponceDto;
import com.SelimGezer.GraduationProject.Service.EntityService.CreditEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/credits")
@Api(value = "Credit Api documentation")
public class CreditController {

    private final CreditEntityService creditEntityService;

    public CreditController(CreditEntityService creditEntityService) {
        this.creditEntityService = creditEntityService;
    }


    @ApiOperation(value = "Get Credit By Identification Number and Birthday Method")
    @GetMapping("/")
    public CreditDetailResponceDto getByCreditIdentificationAndBirthday(@RequestParam(value = "identificationNumber",required = true) @ApiParam(value = "Identification Number",example = "12312312313") Long identificationNumber,
                                                                        @RequestParam(value = "birthday",required = true)  @ApiParam(value = "Birthday Date",example = "1997-08-17") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday){
        return creditEntityService.getByCreditIdentificationAndBirthday(identificationNumber,birthday);
    }

}
