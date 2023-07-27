# Base image
#베이스 이미지를 사용하여 빌더 스테이지를 설정합니다.
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set working directory  작업 디렉토리로 설정합니다.
WORKDIR /app

# Copy the Maven project file(s) Maven 프로젝트 파일인 pom.xml을 복사합니다
COPY pom.xml .

# Download dependencies 의존성을 다운로드합니다.
#RUN mvn dependency:go-offline -B

# Copy the project source code 프로젝트 소스 코드를 복사합니다.
COPY src ./src

# Build the application 애플리케이션을 빌드합니다.
RUN mvn package

# Final image 베이스 이미지를 사용하여 최종 이미지 스테이지를 설정합니다.
FROM openjdk:17-jdk-alpine AS final

# Set working directory  디렉토리를 작업 디렉토리로 설정합니다.
WORKDIR /app

# Copy the built artifact from the builder stage
# 빌더 스테이지에서 빌드된 artifact를 /app/app.jar로 복사합니다.
COPY --from=builder /app/target/*.jar ./app.jar

# Expose the application port
EXPOSE 8082

# Run the application
CMD ["java", "-jar", "app.jar"]