package domain

import domain.GraphObject._

object QueryDSL {

  sealed trait BaseQuery

  case class AddFriend(person: PersonNode, friend: PersonNode) extends BaseQuery
  case class AddRelative(person: PersonNode, relative: PersonNode) extends BaseQuery
  case class AddWorker(business : BusinessNode, worker: PersonNode) extends BaseQuery
  case class FindRelatives(person: PersonNode) extends BaseQuery
  case class FindWorkers(business: BusinessNode) extends BaseQuery
  case class RemoveFriend(person: PersonNode, friend: PersonNode) extends BaseQuery
  case class RemoveRelative(person: PersonNode, relative: PersonNode) extends BaseQuery
  case class RemoveWorker(business : BusinessNode, worker: PersonNode) extends BaseQuery


  case class Query(from: Option[Node], to: Option[Node], rel: Option[RelationType]) extends BaseQuery
  case class QueryAll(node: Node) extends BaseQuery
  case class Add(obj: Either[Node, Edge]) extends BaseQuery
  case class Remove(obj: Either[Node, Edge]) extends BaseQuery
  case class Update(obj1: Either[Node, Edge], obj2: Either[Node, Edge]) extends BaseQuery


}
