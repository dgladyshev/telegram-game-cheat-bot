FROM b.gcr.io/cubic/java:latest

ADD . /telegram-game-cheat-bot

WORKDIR /telegram-game-cheat-bot

ENV MEMORY 512

EXPOSE 8080

RUN gradle clean build

CMD java -Xmx${MEMORY}m -Djava.security.egd=file:/dev/./urandom -jar build/libs/telegram-game-cheat-bot.jar
