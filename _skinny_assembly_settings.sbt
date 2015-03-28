import AssemblyKeys._

assemblySettings

mainClass in assembly := Some("skinny.standalone.JettyLauncher")

_root_.sbt.Keys.test in assembly := {}

resourceGenerators in Compile <+= (resourceManaged, baseDirectory) map { (managedBase, base) =>
  val webappBase = base / "src" / "main" / "webapp"
  for ( (from, to) <- webappBase ** "*" `pair` rebase(webappBase, managedBase / "main/") )
  yield {
    Sync.copy(from, to)
    to
  }
}

