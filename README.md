# MottuSense-Cloud

# 🚀 MottuSense - API em Java 21 no Azure

## 📌 Descrição da Solução
O **MottuSense** é uma aplicação desenvolvida em **Java 21** utilizando **Spring Boot**, que oferece uma **API REST** para gerenciamento de usuários.  
A aplicação foi publicada no **Azure App Service** e utiliza o **Azure SQL Database** como camada de persistência.  

A infraestrutura foi provisionada e configurada 100% via **Azure CLI**, garantindo automação, consistência e alinhamento às práticas de **DevOps e Cloud Computing**.  
Foram implementadas operações de **CRUD (Create, Read, Update, Delete)** para entidades de usuários, incluindo dados pessoais, configurações e relacionamento com filiais.  

---

## 💡 Benefícios para o Negócio
- **Centralização dos dados:** os registros de usuários ficam em um banco SQL Server em nuvem, com segurança, backup e alta disponibilidade.
- **Escalabilidade:** o App Service permite que a API cresça de acordo com a demanda, sem necessidade de reconfigurações complexas.
- **Automação e DevOps:** uso de scripts via CLI para provisionamento da infraestrutura, reduzindo erros e garantindo rastreabilidade.
- **Acessibilidade global:** a API está exposta em uma URL pública, podendo ser consumida por qualquer sistema ou cliente.
- **Facilidade de manutenção:** variáveis de ambiente (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`) foram configuradas diretamente no App Service, permitindo ajustes sem alterar o código.
- **Base para expansão:** a arquitetura já suporta integração futura com módulos de frota, relatórios analíticos e sistemas corporativos.

---

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java 21  
- **Framework:** Spring Boot  
- **Banco de Dados:** Azure SQL Database  
- **Hospedagem:** Azure App Service (Plano B1)  
- **Automação:** Azure CLI  
- **Controle de versão:** Git + GitHub  

---

## 🔧 Passos Principais do Deploy
1. Criação do **Resource Group** e **App Service Plan**.  
2. Criação do **App Service** configurado para Java 21.  
3. Provisionamento do **Azure SQL Server** e do banco **MottuSenseDB**.  
4. Configuração de **firewall e app settings** (variáveis de ambiente).  
5. Deploy do **artefato JAR** com o comando `az webapp deploy`.  
6. Testes de **CRUD de usuários** utilizando `curl` com autenticação básica.  

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
