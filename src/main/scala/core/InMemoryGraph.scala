package core

import domain.Graph
import domain.GraphObject._

import scala.collection.mutable.{Set => MSet}


class InMemoryGraph extends Graph {

  val edges = MSet.empty[Edge]

  def findEdges(predicate: PartialFunction[Edge, Boolean]): Set[Edge] = edges.filter(predicate).toSet

  def add(node: Node): Option[Node] = None

  def add(edge: Edge): Option[Edge] = {
    try {
      edges.add(edge)
      Some(edge)
    } catch {
      case _: Throwable => None
    }
  }

  def remove(node: Node): Set[Edge] = {
    try {
      val edgesToRemove = findAll(node)
      edges --= edgesToRemove
      edgesToRemove
    } catch {
      case _: Throwable => Set.empty
    }
  }

  def remove(edge: Edge): Option[Edge] = {
    try {
      edges.remove(edge)
      Some(edge)
    } catch {
      case _: Throwable => None
    }
  }

}