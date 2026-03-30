package com.example.algamoney.api.exceptionhandler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@ControllerAdvice // é uma anotação do Spring que indica que essa classe é um manipulador de exceções global, ou seja,
// ela será responsável por tratar as exceções lançadas em toda a aplicação.
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {


    @Autowired
    private MessageSource messageSource; // é uma interface do Spring que permite acessar mensagens de erro
    // definidas em arquivos de propriedades, como o messages.properties. Com essa interface,

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause().toString();
        List<Erro> erros = Arrays.asList (new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
        HttpHeaders headers, HttpStatus status, WebRequest request){

            List<Erro> erros = criarListaDeErros(ex.getBindingResult());
            return handleExceptionInternal (ex, erros, headers, HttpStatus.BAD_REQUEST, request);

        }
    private List<Erro> criarListaDeErros(BindingResult bindingResult){
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }
    

    public static class Erro {

        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
    }
}
