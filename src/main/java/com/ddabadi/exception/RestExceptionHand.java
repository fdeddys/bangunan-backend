package com.ddabadi.exception;

import com.ddabadi.dto.ErrorInfo;
import com.ddabadi.service.SupplierService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by deddy on 6/4/16.
 */
@ControllerAdvice
public class RestExceptionHand {


    Logger logger = Logger.getLogger(RestExceptionHand.class);

    @ExceptionHandler(SupplierTidakValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo SupplierError(HttpServletRequest req, SupplierTidakValidException supplierTidakValidException){

        String errMess="Supplier tidak valid = " + supplierTidakValidException.getPesan() ;
        String errUrl = req.getRequestURL().toString();
        logger.info("Supplier tidak valid Exception");
        return new ErrorInfo(errUrl,errMess);
    };

    @ExceptionHandler(SaldoCustomerTidakCukupException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    public ErrorInfo SaldoCustTidakCukup(HttpServletRequest request, SaldoCustomerTidakCukupException saldoCustomerTidakCukupException){
        String errMess = "saldo tidak cukup " + saldoCustomerTidakCukupException.getPesan();
        String errUrl = request.getRequestURL().toString();

        logger.info("Saldo tidak cukup");
        return new ErrorInfo(errUrl,errMess);
        //return "saldo tidak cukup";
    }

    @ExceptionHandler(StokTidakCukupException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    public ErrorInfo StokTidakCukup(HttpServletRequest request, StokTidakCukupException stokTidakCukupException){
        String errMess = "Stok tidak cukup " + stokTidakCukupException.getPesan();
        String errUrl = request.getRequestURL().toString();

        logger.info("Stok tidak cukup exception");
        return new ErrorInfo(errUrl,errMess);
        //return "saldo tidak cukup";
    }

    @ExceptionHandler(TanggalTidakValidException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    public ErrorInfo TanggalTidakValid(HttpServletRequest request, TanggalTidakValidException tanggalTidakValidException){
        String errMess = "Tanggal tidak valid =" + tanggalTidakValidException.getPesan();
        String errUrl = request.getRequestURL().toString();

        logger.info("Tanggal tidak valid  ");
        return new ErrorInfo(errUrl,errMess);

    }

    @ExceptionHandler(CustomerTidakValidException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    public ErrorInfo CustomerTidakValid(HttpServletRequest request, CustomerTidakValidException customerTidakValidEx){
        String errMess = "Error Customer = " + customerTidakValidEx.getPesan();
        String errUrl = request.getRequestURL().toString();

        logger.info("customer error");
        return new ErrorInfo(errUrl,errMess);
    }

    @ExceptionHandler(MandorTidakValid.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    public ErrorInfo MandorTidakValid(HttpServletRequest request, MandorTidakValid  mandorTidakValid){
        String errMess = "Error Mandor = " + mandorTidakValid.getPesan();
        String errUrl = request.getRequestURL().toString();

        logger.info("customer error");
        return new ErrorInfo(errUrl,errMess);
    }


}
