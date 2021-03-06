package ca.polymtl.inf8405.sevenwonders.model

import org.specs2.mutable._
import SevenWonders._

class GameSetupSpec extends Specification {
  "A classicGameSetup" should {
    "correclty generate the cards to play with during the three ages" in {
      val cards = classicSevenWonders.generateCards(3)
      cards(1).size === 21
      cards(2).size === 21
      cards(3).size === 21

      cards.size ==== 3

      cards(3).count(_.isInstanceOf[GuildCard]) === 5
    }
  }
}
