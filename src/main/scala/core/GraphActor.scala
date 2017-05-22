package core

import akka.actor.Actor
import akka.event.LoggingReceive
import domain.Graph
import domain.GraphObject._
import domain.QueryDSL._



trait GraphActor extends Actor{

  def graph : Graph

  def receive = LoggingReceive {

    case AddFriend(person, friend) => sender ! {
      for {
        f1 <- graph.add(person -> FriendOf -> friend)
        f2 <- graph.add(friend -> FriendOf -> person)
      } yield f2
    }
    case AddRelative(person, relative) => sender ! {
      for {
        r1 <- graph.add(person -> RelativeOf -> relative)
        r2 <- graph.add(relative -> RelativeOf -> person)
      } yield r2
    }
    case AddWorker(business , worker) => sender ! graph.add(worker -> WorksAt -> business)

    case RemoveFriend(person, friend) => sender ! {
      for {
        f1 <- graph.remove(person -> FriendOf -> friend)
        f2 <- graph.remove(friend -> FriendOf -> person)
      } yield f2
    }
    case RemoveRelative(person, relative) => sender ! {
      for {
        r1 <- graph.remove(person -> RelativeOf -> relative)
        r2 <- graph.remove(relative -> RelativeOf -> person)
      } yield r2
    }
    case RemoveWorker(business , worker) => sender ! graph.remove(worker -> WorksAt -> business)

    case FindWorkers(business) => sender ! graph.findOutgoing(business, RelativeOf)
    case FindRelatives(person) => sender ! graph.findOutgoing(person, WorksAt)

    case QueryAll(node) => sender ! graph.findAll(node)
    case Query(Some(from), None, None) => sender ! graph.findOutgoing(from)
    case Query(None, Some(to), None) => sender ! graph.findIngoing(to)
    case Query(None, None, Some(rel)) => sender ! graph.findAll(rel)
    case Query(Some(from), None, Some(rel)) => sender ! graph.findOutgoing(from, rel)
    case Query(None, Some(to), Some(rel)) => sender ! graph.findIngoing(to, rel)
    case Query(Some(from), Some(to), None) => sender ! graph.findBetween(from, to)
    case Query(Some(from), Some(to), Some(rel)) => sender ! graph.exists(Edge(from, rel,to ))
    case Add(Left(node)) => sender ! graph.add(node)
    case Add(Right(edge)) => sender ! graph.add(edge)
    case Remove(Left(node)) => sender ! graph.remove(node)
    case Remove(Right(edge)) => sender ! graph.remove(edge)
    case Update(Left(node1), Left(node2)) => sender ! graph.update(node1, node2)
    case Update(Right(edge1), Right(edge2)) => sender ! graph.update(edge1, edge2)

  }

}