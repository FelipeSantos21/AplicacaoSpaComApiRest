@echo off
start cmd /k java -jar ApiRest.jar
echo "Esperando a API Rest acabar de iniciar para abrir o navegador..."
TIMEOUT 10
start Front.html