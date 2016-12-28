import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import net.logstash.logback.appender.LogstashTcpSocketAppender
import net.logstash.logback.encoder.LogstashEncoder
import org.slf4j.MDC

import static ch.qos.logback.classic.Level.*

def appenderList = ["CONSOLE"]

def sourceType = "server"
def sourceId = "telegram-game-cheat-bot"
def logstashHost = System.getenv("LOGSTASH_HOST")
def logstashPort = System.getenv("LOGSTASH_PORT")

jmxConfigurator()

logger('org.springframework', OFF)
logger('org.springframework.boot', OFF)
logger('org.springframework.web.servlet', OFF)
logger('org.springframework.security.web', OFF)
logger('org.springframework.context.support', OFF)
logger("com.dgladyshev", DEBUG)
logger("org.hibernate", OFF)

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
    }
}

if (logstashHost != null) {
    appender("logstash", LogstashTcpSocketAppender) {
        remoteHost = logstashHost
        port = logstashPort.toInteger()
        encoder(LogstashEncoder)
    }
    appenderList.add("logstash")
}

MDC.put("source_type", sourceType)
MDC.put("source_id", sourceId)
root(DEBUG, appenderList)