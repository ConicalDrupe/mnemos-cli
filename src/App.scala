import com.monovore.decline._
import cats.syntax.all._

// We'll start by defining our individual options...
val ModeOpt = Opts
  .option[String](
    "run-mode",
    "Run mode of cli. (note) by default, there is also run,report"
  )
  .withDefault("note")
val tagOpt = Opts
  .option[String]("tag", "tag used for labeling notes and filtering run/report")
val palaceOpt = Opts.option[String](
  "palace",
  "palace is same as tag used for labeling notes and filtering run/report"
)

// ...along with a case class that captures all our configuration data.
sealed trait InputConfig
case class MemoryConfig(tag: String, palace: String) extends InputConfig
case class RunConfig(mem: Boolean, run: Boolean, report: boolean)
    extends InputConfig

case class Config(
    runConfig: RunConfig,
    memoryConfig: MemoryConfig
)

val configOpts: Opts[Config] = (MemoryConfig, RunConfig)

// And finally, we pass the validated config to a `run` function that does the real work.
def runApp(config: Config) =
  (MemoryConfig, RunConfig).mapN(Config.apply)
// res0: Opts[Nothing] = Opts([--input-uri <uri>] [--timeout <duration>] [--input-file <path>] <output-file>)
