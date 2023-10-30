# 使用 Java 17 的 OpenJDK 镜像作为基础
FROM openjdk:8-jdk

# 设置工作目录
WORKDIR /app

# 复制你的 JAR 文件到工作目录
COPY target/commerce.jar /app/app.jar

# 设置启动命令来运行你的 JAR 文件
CMD ["java", "-jar", "/app/app.jar"]
