package domain

import GraphObject._
import scala.language.postfixOps

trait Graph {

  def add(node: Node): Option[Node]
  def add(edge: Edge): Option[Edge]

  def remove(node: Node): Set[Edge]
  def remove(edge: Edge): Option[Edge]

  // method to query Set[Edge] that satisfies predicate
  def findEdges(predicate: PartialFunction[Edge, Boolean]): Set[Edge]

  def findOutgoing(from: Node): Set[Edge] =
    findEdges { case Edge(from2, _, _) => from == from2}
  def findIngoing(to: Node): Set[Edge] =
    findEdges { case Edge(_, _ ,to2) => to == to2}
  def findOutgoing(from: Node, relationtype: RelationType): Set[Edge] =
    findEdges { case Edge(from2,rel, _) => from == from2 && rel == relationtype }
  def findIngoing(to: Node, relationtype: RelationType): Set[Edge] =
    findEdges { case Edge(_, rel, to2) => to == to2 && rel == relationtype}
  def findBetween(from: Node, to: Node): Set[Edge] =
    findEdges { case Edge(from2, _, to2) => from == from2 && to == to2}
  def findAll(relationtype: RelationType): Set[Edge] =
    findEdges { case Edge(_,rel, _) => rel == relationtype }
  def findAll(node: Node): Set[Edge] =
    findEdges { case Edge(from, _, to) => node == from || node == to}
  def exists(edge: Edge): Option[Edge] =
    findEdges { case e: Edge => e == edge} headOption
  def findOutgoingCardinality(to: Node, relationtype: RelationType) : Int =
    findOutgoing(to,relationtype).size
  def findIncomingCardinality(to: Node, relationtype: RelationType) : Int =
    findIngoing(to,relationtype).size

  def update(oldNode: Node, newNode: Node): Set[Edge] =
    for {
      e@Edge(to,rel,from) <- findAll(oldNode)
      newTo = if (to == oldNode) newNode
      else to
      newFrom = if (from == oldNode) newNode else from
      removed <- remove(e)
      added <- add(Edge(newTo, rel, newFrom))
    } yield added

  def update(oldEdge: Edge, newEdge: Edge): Option[Edge] =
    for {
      removed <- remove(oldEdge)
      added <- add(newEdge)
    } yield added

}

