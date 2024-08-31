import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

object Edge {

  def location(uri: String, envVars: Map[String, String]): String = {
    val PZ_NAME = envVars.getOrElse("PZ_NAME", "NOT_SET")
    val REGION = envVars.getOrElse("REGION", "NOT_SET")
    val NS = envVars.getOrElse("NS", "NOT_SET")

    val ROOT_URI = s"intelligence-edge-$PZ_NAME-$REGION-$NS"

    val now = LocalDateTime.now()
    val currentDate = now.format(DateTimeFormatter.ofPattern("D_yyyy-MM-dd"))
    val currentTime = now.format(DateTimeFormatter.ofPattern("'T'_HH-mm-ss"))
    val uniqueId = UUID.randomUUID().toString

    uri.replace("$ROOT", ROOT_URI)
      .replace("$DATE", currentDate)
      .replace("$TIME", currentTime)
      .replace("$UUID", uniqueId)
  }
}

/*
object MainApp {
  def main(args: Array[String]): Unit = {
    val envVars = Map(
      "PZ_NAME" -> "test",
      "REGION" -> "us-west-2",
      "NS" -> "dev"
    )

    val sample = Edge.location(
      "s3://$ROOT/Feast_features_spaces_comparison/datasets/$DATE/$TIME/$UUID/", 
      envVars
    )
    println(sample)
  }
}
*/

