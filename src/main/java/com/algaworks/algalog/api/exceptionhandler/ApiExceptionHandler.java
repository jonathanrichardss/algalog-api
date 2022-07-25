package com.algaworks.algalog.api.exceptionhandler;

import com.algaworks.algalog.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.algaworks.algalog.api.exceptionhandler.ProblemaException;

import javax.websocket.MessageHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {



        List<ProblemaException.Campo> campos = new ArrayList<>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new ProblemaException.Campo(nome, mensagem));
        }

        ProblemaException problemaException = new ProblemaException();
        problemaException.setStatus(status.value());
        problemaException.setDataHora(LocalDateTime.now());
        problemaException.setCampos(campos);
        problemaException.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");

        return handleExceptionInternal(ex, problemaException, headers, status, request);

        }


        @ExceptionHandler(NegocioException.class)
        public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ProblemaException problema = new ProblemaException();
        problema.setStatus(status.value());
        problema.setDataHora(LocalDateTime.now());
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);

    }

    }

