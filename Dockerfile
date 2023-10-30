# Use a imagem base do Java
FROM eclipse-temurin:17-jdk-focal

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie a pasta .mvn para o contêiner
COPY .mvn/ ./.mvn/

# Copie os arquivos mvnw e pom.xml para o contêiner
COPY mvnw pom.xml ./

# Certifique-se de que o Maven Wrapper seja executável
RUN chmod +x mvnw

# Execute o comando Maven Wrapper para baixar as dependências
RUN ./mvnw dependency:go-offline

# Copie o restante do código-fonte do aplicativo para o contêiner
COPY src ./src

# Comando de execução do aplicativo
CMD ["./mvnw", "spring-boot:run"]
