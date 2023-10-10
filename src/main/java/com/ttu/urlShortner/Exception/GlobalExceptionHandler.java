package com.ttu.urlShortner.Exception;

import com.ttu.urlShortner.Dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ParsingException.class)
    public ResponseEntity<ErrorMessageDto> handleParsingException(ParsingException exception, WebRequest request)
    {
        ErrorMessageDto errorMessage = new ErrorMessageDto(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileWritingException.class)
    public ResponseEntity<ErrorMessageDto> handleFileWritingException(FileWritingException exception, WebRequest request)
    {
        ErrorMessageDto errorMessage = new ErrorMessageDto(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CsvFileNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleCsvFileNotFoundException(CsvFileNotFoundException exception, WebRequest request)
    {
        ErrorMessageDto errorMessage = new ErrorMessageDto(new Date(), exception.getMessage(), request.getDescription(false));
        System.out.println(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchRecordException.class)
    public ResponseEntity<ErrorMessageDto> handleNoSuchRecordException(NoSuchRecordException exception, WebRequest request)
    {
        ErrorMessageDto errorMessage = new ErrorMessageDto(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShortUrlGenerationException.class)
    public ResponseEntity<ErrorMessageDto> handleShortUrlGenerationException(ShortUrlGenerationException exception, WebRequest request)
    {
        ErrorMessageDto errorMessage = new ErrorMessageDto(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }












}
