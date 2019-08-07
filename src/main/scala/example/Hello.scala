package example

import akka.Done
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.ws.ahc.{AhcWSClientConfigFactory, StandaloneAhcWSClient}

object Hello extends App {
  import play.api.libs.ws.DefaultBodyWritables._
  import scala.concurrent.ExecutionContext.Implicits._

  val logger: Logger = LoggerFactory.getLogger(Hello.getClass)

  val conf = ConfigFactory.load()

  val applicationKey = conf.getString("betfair.application-key")
  val username = conf.getString("betfair.username")
  val password = conf.getString("betfair.password")

  val body = s"username=$username&password=$password"

  implicit val system = ActorSystem()
  system.registerOnTermination {
    System.exit(0)
  }
  implicit val materializer = ActorMaterializer()

  val config = AhcWSClientConfigFactory.forConfig(conf)
  logger.info("keyStoreConfigs -> {}", config.wsClientConfig.ssl.keyManagerConfig.keyStoreConfigs)
  val ws = StandaloneAhcWSClient(config)

  logger.info("ws -> {}", ws)

  val request = ws.url("https://identitysso-cert.betfair.com/api/certlogin").withHttpHeaders(("X-Application", applicationKey))

  logger.info("request -> {}", request)
  logger.info("headers -> {}", request.headers)
  logger.info("params -> {}", body)
  logger.info("body -> {}", body)

  request.post(body).map { response =>
    logger.info("response -> {} {}", response.contentType, response.body)
    Done
  }
}

