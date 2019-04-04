package com.perseus

import TestUtils._
import freestyle.tagless.logging.LoggingM
import sourcecode.{File, Line}
import cats.implicits._

class ConsoleLogging extends LoggingM.Handler[Test] {
  override def debug(msg: String, sourceAndLineInfo: Boolean)(implicit line: Line, file: File): FS[Unit] = ???

  override def debugWithCause(msg: String, cause: Throwable, sourceAndLineInfo: Boolean)(implicit line: Line, file: File): FS[Unit] = ???

  override def error(msg: String, sourceAndLineInfo: Boolean)(implicit line: Line, file: File): FS[Unit] = ???

  override def errorWithCause(msg: String, cause: Throwable, sourceAndLineInfo: Boolean)(implicit line: Line, file: File): FS[Unit] = ???

  override def info(msg: String, sourceAndLineInfo: Boolean)(implicit line: Line, file: File): FS[Unit] = println(msg).pure[FS]

  override def infoWithCause(msg: String, cause: Throwable, sourceAndLineInfo: Boolean)(implicit line: Line, file: File): FS[Unit] = ???

  override def warn(msg: String, sourceAndLineInfo: Boolean)(implicit line: Line, file: File): FS[Unit] = ???

  override def warnWithCause(msg: String, cause: Throwable, sourceAndLineInfo: Boolean)(implicit line: Line, file: File): FS[Unit] = ???
}
