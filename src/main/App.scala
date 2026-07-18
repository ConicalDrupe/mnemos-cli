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
case class RunConfig(mem: Boolean, run: Boolean, report: Boolean)
    extends InputConfig

case class Config(
    runConfig: RunConfig,
    memoryConfig: Optional[MemoryConfig]
)

val configOpts: Opts[Config] = (RunConfig, MemoryConfig.orNone).mapN(Config.apply)
// And finally, we pass the validated config to a `run` function that does the real work.
@main def runApp(config: Config): Unit = 
  configOpts.map(runApp)
