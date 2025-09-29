# MottuSense-Java

- Vitor Hugo da Silva - 558961
- Brendon de Paula - 559196
- João Henrique Murilla Ganança - 556405

# Sistema de Gestão de Usuários e Monitoramento de Motos

Este projeto é um serviço que permite o **cadastro de usuários**, a **localização de filiais mais próximas com base no CEP** e o **monitoramento inteligente de motos** através de alertas em tempo real. É parte da solução MottuSense voltada para a gestão e operação de veículos em pátios.

---

## Funcionalidades

### Gestão de Usuários
- Cadastro de novos usuários  
![image](https://github.com/user-attachments/assets/333554ff-16f9-4917-b4b7-f7e7a71ee066)
- Consulta de usuários cadastrados  
![image](https://github.com/user-attachments/assets/d5584ea0-a16c-43ee-844d-316afe148ab2)
- Consulta de usuários por ID  
![image](https://github.com/user-attachments/assets/9f94c655-5e7a-4b00-b9cd-b60fe6778b1a)
- Atualização de dados dos usuários  
![image](https://github.com/user-attachments/assets/00274857-c294-454a-83e0-6c43238960f2)
- Remoção de usuários  
![image](https://github.com/user-attachments/assets/6aa051d2-5d03-4bd6-8e38-02d0b7ae8ce9)  
*OBS: Retorna status 204 (No Content) ao deletar*

### Gestão de Filiais
- Cadastro de filiais  
![image](https://github.com/user-attachments/assets/68c41186-545a-4818-9208-a08d85302a12)
- Consulta de filiais  
![image](https://github.com/user-attachments/assets/40fb35a3-407b-4f50-8ade-b02e2abd79fd)
- Consulta de filiais por ID  
![image](https://github.com/user-attachments/assets/2cac60e6-45e5-456c-845a-1fbad4a300db)
- Atualização de filiais  
![image](https://github.com/user-attachments/assets/529d5648-2e6b-49ae-a0d4-a0b48cedb535)
- Remoção de filiais  
![image](https://github.com/user-attachments/assets/548a352f-f1e3-469d-8ba4-a2ae4f091361)  
*OBS: Retorna status 204 (No Content) ao deletar*

### Localização de Filiais
- Localiza filiais mais próximas do usuário com base no seu CEP
- Utiliza serviços de geolocalização e cálculo de distância

### Sistema de Alertas de Motos
Alertas automáticos baseados no comportamento das motos:
- **Entrada no pátio**: Notificação quando a moto entra em uma filial
- **Saída do pátio**: Notificação quando a moto sai de uma filial
- **Sem placa**: Alerta para motos que chegam sem identificação
- **Manutenção necessária**: Aviso para revisões programadas
- **Pronta para locação/venda**: Status de disponibilidade comercial

### Integração com Serviços Externos
- Consulta de CEP via API ViaCEP utilizando Feign Client
- Preparado para integração com outros serviços do ecossistema

---

## Exemplos de Alertas
| Tipo de Alerta              | Descrição                                                                 |
|-----------------------------|---------------------------------------------------------------------------|
| Entrada no pátio            | "A moto com ID 1234 entrou na filial SP-01 às 15h"                        |
| Saída do pátio              | "A moto com ID 5678 saiu da filial RJ-02 às 17h"                          |
| Moto sem placa              | "A moto com ID 9876 chegou no pátio sem placa instalada"                  |
| Manutenção necessária       | "A moto com ID 1122 está com 10.000km e precisa de revisão"               |
| Pronta para locação/venda   | "A moto com ID 3344 está limpa, revisada e disponível"                    |

---

## Segurança
- Validações de entrada com Bean Validation (@Valid em controllers)

---

## Tecnologias Utilizadas

### Spring Boot 3.4.5
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- spring-boot-starter-validation
- spring-boot-starter-test
- spring-boot-devtools

### Integração
- spring-cloud-starter-openfeign

### Banco de Dados
- Oracle JDBC Driver (ojdbc11)

### Outras Bibliotecas
- Lombok 1.18.36
- ModelMapper 3.0.0

### Ambiente
- Java 21

![image](https://github.com/user-attachments/assets/b25bd178-9471-423c-aa61-d36bb7294c5e)

---

## Configuração e Execução

1. **Clone o repositório**
```bash
git clone https://github.com/vitorvhsilva/MottuSense-Java.git
cd mottusense-users
```

2. **Configure o banco de dados**
```bash
# spring.datasource.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL
# spring.datasource.username=RM556405
# spring.datasource.password=Fiap#2025
# spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
# spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
# #spring.jpa.show-sql=true
# spring.jpa.properties.hibernate.format_sql=true
# spring.jpa.hibernate.ddl-auto=create-drop
```
3. **Build do projeto**
```bash
mvn clean install
```
4. **Rodar o seguinte comando no ORACLE SQL DEVELOPER na conta que está no application.yaml**
   ----------------------------------------------------
   PURGE RECYCLEBIN;
BEGIN
  -- Deletar Constraints
  FOR r IN (SELECT constraint_name, table_name 
            FROM user_constraints 
            WHERE constraint_type IN ('P', 'R', 'U')) LOOP
    EXECUTE IMMEDIATE 'ALTER TABLE "' || r.table_name || '" DROP CONSTRAINT "' || r.constraint_name || '"';
  END LOOP;
  -- Deletar Sequences
  FOR r IN (SELECT sequence_name FROM user_sequences) LOOP
    EXECUTE IMMEDIATE 'DROP SEQUENCE "' || r.sequence_name || '"';
  END LOOP;
  -- Deletar Tabelas
  FOR r IN (SELECT table_name FROM user_tables) LOOP
    EXECUTE IMMEDIATE 'DROP TABLE "' || r.table_name || '" CASCADE CONSTRAINTS PURGE';
  END LOOP;
END;
/

6. **Rodar a aplicação**
Rodar o arquivo UsersApplication

7. **Acesse a API**

A aplicação será iniciada em: http://localhost:8080
