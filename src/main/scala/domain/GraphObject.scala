package domain


import scala.language.implicitConversions

object GraphObject {

  sealed abstract class RelationType
  case object FriendOf extends RelationType
  case object RelativeOf extends RelationType
  case object WorksAt extends RelationType


  sealed trait Node { def id: String}
  case class BusinessNode(id: String) extends Node
  case class PersonNode(id: String)  extends Node


  case class Edge(from: Node, relationType: RelationType, to: Node)

  object Edge {
    def apply(value: ((Node, RelationType), Node)) : Edge = {
      val ((from, rel), to) = value
      Edge(from, rel, to)
    }
  }

  implicit def NodesToEdge(value: ((Node, RelationType), Node)) : Edge = {
    val ((from, rel), to) = value
    Edge(from, rel, to)
  }

}
