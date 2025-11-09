# MottuSense-Cloud

# 🚀 MottuSense - API de Usuários (Java 21 + Spring Boot)

## Arquitetura do Projeto
<img src="https://github.com/vitorvhsilva/MottuSense-dotNet/blob/main/assets/arquitetura_mottusense.png">
Essa é a solução completa que vamos entregar pra Mottu.

## 📌 Descrição da Solução
O **MottuSense** é uma aplicação desenvolvida em **Java 21** com **Spring Boot**, estruturada em camadas (domain, repository, service e controller).  
A aplicação expõe uma **API REST** para gerenciamento de usuários, incluindo informações pessoais, filiais, configurações e localizações.  

Foi projetada para rodar no **Azure App Service**, com persistência de dados em **Azure SQL Database**.  
A infraestrutura de nuvem foi provisionada via **Azure CLI**, garantindo automação e padronização do ambiente.  

---

## 💡 Funcionalidades
- Cadastro de usuários (nome, CPF, telefone, e-mail, senha, data de nascimento).  
- Associação de usuários a **filiais** e **localizações**.  
- Configuração personalizada por usuário (ex.: alertas de entrada/saída de motos no pátio).  
- Operações de **CRUD (Create, Read, Update, Delete)** expostas em endpoints REST.  
- Autenticação básica via **Spring Security** (usuário: `user` / senha: `teste`).  

---

## 💼 Benefícios para o Negócio
- **Gestão centralizada de usuários:** todos os dados ficam armazenados em nuvem com segurança e alta disponibilidade.  
- **Escalabilidade:** o App Service permite aumento de capacidade sob demanda.  
- **Automação DevOps:** toda a criação de recursos e deploy é feita por **Azure CLI**, reduzindo erros manuais.  
- **Expansão futura:** base sólida para integrar novos módulos (ex.: gestão de frota, relatórios de eventos, monitoramento).  
- **Flexibilidade de integração:** a API pode ser consumida por aplicações web, mobile ou sistemas corporativos.  

---

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java 21  
- **Framework:** Spring Boot 3.x  
- **Banco de Dados:** Azure SQL Database  
- **ORM:** Spring Data JPA / Hibernate  
- **Segurança:** Spring Security (Basic Auth)  
- **Hospedagem:** Azure App Service  
- **Automação:** Azure CLI 

---

## 🔧 Passos Principais do Deploy
1. Criação do **Resource Group** e **App Service Plan**.  
2. Criação do **App Service** configurado para Java 21.  
3. Provisionamento do **Azure SQL Server** e do banco **MottuSenseDB**.  
4. Configuração de **firewall e app settings** (variáveis de ambiente).  
5. Deploy do **artefato JAR** com o comando `az webapp deploy`.  
6. Testes de **CRUD de usuários** utilizando `curl` com autenticação básica.  

---

# Video completo testando a API.

## https://youtu.be/aTSQPyF1W28

---

# Guia rápido para criar e publicar a API MottuSense no Azure com Java 21 e SQL Server.

```bash
az group create --name mottusense-rg --location brazilsouth

az appservice plan create --name mottusense-plan --resource-group mottusense-rg --sku B1

az webapp create --resource-group mottusense-rg --plan mottusense-plan --name mottusense-api --runtime "JAVA:21" --deployment-local-git

az webapp config set --resource-group mottusense-rg --name mottusense-api --use-32bit-worker-process false

az sql server create --name mottusensesqlserver --resource-group mottusense-rg --location brazilsouth --admin-user sqladmin --admin-password SenhaForte!123

az sql db create --resource-group mottusense-rg --server mottusensesqlserver --name MottuSenseDB --service-objective S0 --backup-storage-redundancy Local

az sql server firewall-rule create --resource-group mottusense-rg --server mottusensesqlserver --name AllowAllIPs --start-ip-address 0.0.0.0 --end-ip-address 255.255.255.255

# configurar variaveis de ambiente

az webapp config appsettings set --name mottusense-api --resource-group mottusense-rg --settings DB_URL="jdbc:sqlserver://mottusensesqlserver.database.windows.net:1433;database=MottuSenseDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;" DB_USERNAME="sqladmin" DB_PASSWORD=SenhaForte!123 

az webapp deploy --resource-group mottusense-rg --name mottusense-api --src-path ./users-0.0.1-SNAPSHOT.jar --type jar
```


# Imagens das tabelas
<img width="1364" height="634" alt="Captura de tela 2025-09-30 225854" src="https://github.com/user-attachments/assets/eae0104b-2d47-4956-b459-d373c4113d74" />

<img width="1365" height="630" alt="Captura de tela 2025-09-30 230004" src="https://github.com/user-attachments/assets/d527a34e-7d5f-4cc6-808d-f8607b0ff549" />


