import com.monovore.decline._
import cats.syntax.all._

// We'll start by defining our individual options...
val modeOpt = Opts
  .argument[String](
    "mode",
  // help="Run mode of cli. (note) by default, there is also run,report"
  )
  .withDefault("note")
val tagOpt = Opts
  .option[String]("tag", short="t", metavar="label", help="tag used for labeling notes and filtering run/report")
val palaceOpt = Opts.option[String](
  "palace", short="p",metavar="location",
  help="palace is same as tag used for labeling notes and filtering run/report"
)

// ...along with a case class that captures all our configuration data.
sealed trait InputConfig
case class MemoryConfig(tag: Option[String], palace: Option[String]) extends InputConfig
case class RunConfig(mode: String) //note, run, report
    extends InputConfig

case class Config(
    runConfig: RunConfig,
    memoryConfig: MemoryConfig
)

val runOpt = (modeOpt).map(RunConfig.apply)
val memoryOpt = (tagOpt.orNone, palaceOpt.orNone).mapN(MemoryConfig.apply)
val configOpts: Opts[Config] = (runOpt, memoryOpt).mapN(Config.apply)

// Where does validation come in?
object MneumosApp extends CommandApp (name = "mem",
  header = "interact with mnemos-cli",
  main = configOpts.map { config => println(s"running with config $config")},
  version = "0.0.x"
  )
