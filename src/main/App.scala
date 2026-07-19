import com.monovore.decline._
import cats.syntax.all._

// We'll start by defining our individual options...
val modeOpt = Opts
  .option[String](
    "mode", short = "m",
    metavar ="mode_type",
  help="Run mode of cli. (note) by default, there is also run,report"
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
case class MemoryConfig(tag: String, palace: String) extends InputConfig
case class RunConfig(mode: String) //note, run, report
    extends InputConfig

case class Config(
    runConfig: RunConfig,
    memoryConfig: Option[MemoryConfig]
)

val runOpt = (modeOpt).map(RunConfig.apply)
val memoryOpt = (tagOpt, palaceOpt).mapN(MemoryConfig.apply)
val configOpts: Opts[Config] = (runOpt, memoryOpt.orNone).mapN(Config.apply)

// Where does validation come in?
object App extends CommandApp (name = "mem",
  header = "interact with mnemos-cli",
  main = configOpts.map { config => println(s"running with config $config")},
  version = "0.0.x"
  )
