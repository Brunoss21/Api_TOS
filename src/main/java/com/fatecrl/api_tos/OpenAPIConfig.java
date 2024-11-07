package com.fatecrl.api_tos;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API TOS",
        version = "1.0.0",
        description = "Documentação da API para o Sistema de Operações Portuárias, esta API é o projeto para P2 da disciplina Desenvolvimento para Servidores II",
        contact = @io.swagger.v3.oas.annotations.info.Contact(
            name = "Desenvolvedor",
            email = "bruno.santos313@fatec.sp.gov.br",
            url = "https://www.linkedin.com/in/bruno-santos-098854115/"
        )
    )
)

public class OpenAPIConfig {

    @Override
    public String toString() {
        return "OpenAPIConfig []";
    }
    // Esta classe pode ser usada para configurações adicionais de OpenAPI, se necessário.
}