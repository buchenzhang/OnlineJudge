# 使用一个基础的Java 8镜像
FROM openjdk:8-jdk

# 设置工作目录
WORKDIR /app

# 将Spring Boot应用的JAR文件复制到容器中
COPY target/OJ-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# 设置容器启动时的默认命令
CMD ["java", "-jar", "app.jar"]
