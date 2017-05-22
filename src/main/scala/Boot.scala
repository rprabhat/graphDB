import akka.actor.{ActorSystem, Props}
import core.{InMemoryGraph, GraphActor}
import domain.Graph


object Boot {

  class InMemoryGraphActor extends GraphActor {
    override def graph: Graph = new InMemoryGraph
  }


  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("graphDB")

    val backend = system.actorOf(Props[InMemoryGraphActor], "repository")

  }

}