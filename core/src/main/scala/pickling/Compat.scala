package scala.pickling

import language.experimental.macros
import scala.language.higherKinds

import scala.reflect.macros.Context
import scala.reflect.api.{Universe => ApiUniverse}
import scala.reflect.runtime.{universe => ru}

import scala.collection.mutable
import scala.collection.immutable
import scala.collection.generic.CanBuildFrom

// this is only necessary because 2.10.x doesn't support macro bundles
object Compat {
  // provides a source compatibility stub
  implicit class HasPt[A, B](t: (A, B)) {
    def pt: A = t._1
  }

  def PicklerMacros_impl[T: c.WeakTypeTag](c: Context)(format: c.Expr[PickleFormat]): c.Expr[SPickler[T]] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with PicklerMacros
    c.Expr[SPickler[T]](bundle.impl[T](format.tree))
  }

  def UnpicklerMacros_impl[T: c.WeakTypeTag](c: Context)(format: c.Expr[PickleFormat]): c.Expr[Unpickler[T]] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with UnpicklerMacros
    c.Expr[Unpickler[T]](bundle.impl[T](format.tree))
  }

  def PickleMacros_pickle[T: c.WeakTypeTag](c: Context)(format: c.Expr[PickleFormat]): c.Expr[format.value.PickleType] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with PickleMacros
    c.Expr[format.value.PickleType](bundle.pickle[T](format.tree))
  }

  def PickleMacros_pickleInto[T: c.WeakTypeTag](c: Context)(builder: c.Expr[PBuilder]): c.Expr[Unit] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with PickleMacros
    c.Expr[Unit](bundle.pickleInto[T](builder.tree))
  }

  def PickleMacros_pickleTo[T: c.WeakTypeTag](c: Context)(output: c.Expr[Output[_]])(format: c.Expr[PickleFormat]): c.Expr[Unit] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with PickleMacros
    c.Expr[Unit](bundle.pickleTo[T](output.tree)(format.tree))
  }

  def UnpickleMacros_pickleUnpickle[T: c.WeakTypeTag](c: Context): c.Expr[T] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with UnpickleMacros
    c.Expr[T](bundle.pickleUnpickle[T])
  }

  def UnpickleMacros_readerUnpickle[T: c.WeakTypeTag](c: Context): c.Expr[T] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with UnpickleMacros
    c.Expr[T](bundle.readerUnpickle[T])
  }

  def UnpickleMacros_readerUnpickleTopLevel[T: c.WeakTypeTag](c: Context): c.Expr[T] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with UnpickleMacros
    c.Expr[T](bundle.readerUnpickleTopLevel[T])
  }

  def ListPicklerUnpicklerMacro_impl[T: c.WeakTypeTag](c: Context)(format: c.Expr[PickleFormat]): c.Expr[SPickler[T] with Unpickler[T]] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with ListPicklerUnpicklerMacro
    c.Expr[SPickler[T] with Unpickler[T]](bundle.impl[T](format.tree))
  }

  def PicklerMacros_dpicklerImpl[T: c.WeakTypeTag](c: Context)(format: c.Expr[PickleFormat]): c.Expr[DPickler[T]] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with PicklerMacros
    c.Expr[DPickler[T]](bundle.dpicklerImpl[T](format.tree))
  }

  def PickleMacros_dpicklerPickle[T: c.WeakTypeTag](c: Context)(picklee: c.Expr[T], builder: c.Expr[PBuilder]): c.Expr[Unit] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with PickleMacros
    c.Expr[Unit](bundle.dpicklerPickle[T](picklee.tree, builder.tree))
  }

  def CurrentMirrorMacro_impl(c: Context): c.Expr[ru.Mirror] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with CurrentMirrorMacro
    c.Expr[ru.Mirror](bundle.impl)
  }

  def FastTypeTagMacros_impl[T: c.WeakTypeTag](c: Context): c.Expr[FastTypeTag[T]] = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with FastTypeTagMacros
    c.Expr[FastTypeTag[T]](bundle.impl[T])
  }

  def FastTypeTagMacros_apply(c: Context)(key: c.Expr[String]): c.Expr[FastTypeTag[t]] forSome { type t } = {
    val c0: c.type = c
    val bundle = new { val c: c0.type = c0 } with FastTypeTagMacros
    c.Expr(bundle.apply(key.tree))
  }
}
