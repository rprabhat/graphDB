package core

import domain.Graph
import domain.GraphObject._
import org.scalatest.{BeforeAndAfterEach, FlatSpec}


trait InitGraph extends BeforeAndAfterEach { this: FlatSpec =>

  val graph: Graph

  val personX = PersonNode("X")
  val X_friend_1 = PersonNode("XF1")
  val X_friend_2 = PersonNode("XF2")
  val X_relative_1 = PersonNode("XR1")
  val X_relative_2 = PersonNode("XR2")
  val businessY = BusinessNode("Y")


  override def beforeEach() {
    graph.add(personX -> WorksAt -> businessY )
    graph.add(personX -> RelativeOf -> X_relative_1)
    graph.add(personX -> RelativeOf -> X_relative_2)

    graph.add(personX -> FriendOf -> X_friend_1)
    graph.add(personX -> FriendOf -> X_friend_2)

  }
}