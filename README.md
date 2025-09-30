# MottuSense-Cloud

Guia rápido para criar e publicar a API MottuSense no Azure com Java 21 e SQL Server.

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
