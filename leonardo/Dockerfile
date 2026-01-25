# --- Stage: Runtime Stage ---
# Use a slim JDK 17 image for the final container
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the JAR from the build stage
COPY leonardo/target/leonardo*.jar leonardo.jar

# Expose the port
EXPOSE 8888

ENV JAVA_OPTS="-Xms512m -Xmx1g -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"
ENV SPRING_PROFILE_ACTIVE="ci"

# Run the application with optimized memory settings for containers
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=${SPRING_PROFILE_ACTIVE} -jar leonardo.jar"]