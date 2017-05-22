package core

import domain.GraphObject._
import org.scalatest.FlatSpec


class NonEmptyInMemorySpec extends FlatSpec with InitGraph {

  val graph = new InMemoryGraph

  it should "all relatives of Person(X)" in {
    assertResult(Set(X_relative_1, X_relative_2)) {
      for {
        Edge(_, _, relative) <- graph.findOutgoing(personX, RelativeOf)
      } yield relative
    }
  }

  it should "list the relatives of an every person who works at Business(Y)" in {
    assertResult(Set[Node](X_relative_1, X_relative_2)) {
      for {
        Edge(worker, _ , _ ) <- graph.findIngoing(businessY,WorksAt)
        Edge(_, _, relative) <- graph.findOutgoing(worker, RelativeOf)
      } yield relative
    }
  }

  it should "List all business with more than Z employees" in {
    assertResult(Set(businessY)) {
      val lists = for {
        Edge(worker, _, business) <- graph.findAll(WorksAt)
      } yield (worker, business)

      lists.groupBy(_._2).filter { case (k, v) => v.size >= 0 }.keySet
    }

  }

  it should "add 3rd friend for Person(X)" in {

    val X_friend_3 = PersonNode("XF3")

    val beforeUpdate: Set[Edge] = Set(personX -> FriendOf -> X_friend_1,
                                      personX -> FriendOf -> X_friend_2)

    val afterUpdate: Set[Edge] = Set(
      personX -> FriendOf -> X_friend_1,
      personX -> FriendOf -> X_friend_2,
      personX -> FriendOf -> X_friend_3)

    assertResult(beforeUpdate) {
      graph.findOutgoing(personX, FriendOf)
    }

    assertResult(Some[Edge](personX -> FriendOf -> X_friend_3)) {
      graph.add(personX -> FriendOf -> X_friend_3)
    }

    assertResult(afterUpdate) {
      graph.findOutgoing(personX, FriendOf)
    }

  }
}