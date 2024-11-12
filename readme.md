./gradlew clean build
docker build -t player-service:latest .
docker run -p 9090:8081 player-service:latest

# PlayerService
