package ca.polymtl.inf8405.sevenwonders.model

import org.specs2.mutable._
import SevenWonders._
import utils.Utils._
import collection.Circle
import collection.conversions._

class BeginGameSpec extends Specification {
  "beginGame" should {
    "have the number of players specified" in {
      val game = beginGame(3)
      game.players.size === 3
    }
    "be random" in {
      val games = List(beginGame(5), beginGame(5), beginGame(5), beginGame(5))
      games.allEqual === false
    }
    "no two players should have the same civ" in {
      val game = beginGame(7)
      game.players.map[Civilization](_.civilization).hasDuplicate === false
    }
    "all players should have 7 cards in their hands" in {
      val game = beginGame(6)
      game.players.map[Int](_.hand.size) ==== new Circle(7,7,7,7,7,7)
    }
    "all players should start with 3 coins" in {
      val game = beginGame(4)
      game.players.map[Int](_.coins) ==== new Circle(3,3,3,3)
    }
    "all players should start with no cards in play" in {
      val game = beginGame(7)
      game.players.map[Int](_.played.size) ==== new Circle(0,0,0,0,0,0,0)
    }
    "all players should start with no stages of wonders built" in {
      val game = beginGame(6)
      game.players.map[Int](_.nbWonders) ==== new Circle(0,0,0,0,0,0)
    }
    "all players should start with no battlemarkers" in {
      val game = beginGame(3)
      game.players.map[Int](_.battleMarkers.size) ==== new Circle(0,0,0)
    }
    "there are the appropriate amount of cards for age 2 and age 3" in {
      val game = beginGame(4)
      game.cards(2).size === 4 * 7
      game.cards(3).size === 4 * 7
    }
  }
}
