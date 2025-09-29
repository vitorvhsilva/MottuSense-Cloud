package br.com.mottusense.users.exception;

public class ConfiguracaoUsuarioNaoEncontradaException extends RuntimeException{
    public ConfiguracaoUsuarioNaoEncontradaException(String message) {
        super(message);
    }
}