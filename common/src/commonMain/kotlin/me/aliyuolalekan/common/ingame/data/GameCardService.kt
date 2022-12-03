package me.aliyuolalekan.common.ingame.data

import me.aliyuolalekan.common.domain.model.Card
import me.aliyuolalekan.common.domain.model.CardNumber
import me.aliyuolalekan.common.domain.model.CardShape
import me.aliyuolalekan.common.ingame.domain.IGameCardService
import me.aliyuolalekan.common.servicelocator.ServiceLocator

class GameCardService : IGameCardService {

    override fun getAllCardsShuffled(): List<Card> = getAllCards().shuffled()

    override fun getAllCards(): List<Card> = listOf(
        // circles
        Card(CardShape.Circle, CardNumber.ONE, CardImages.circleOne),
        Card(CardShape.Circle, CardNumber.TWO, CardImages.circleTwo),
        Card(CardShape.Circle, CardNumber.THREE, CardImages.circleThree),
        Card(CardShape.Circle, CardNumber.FOUR, CardImages.circleFour),
        Card(CardShape.Circle, CardNumber.FIVE, CardImages.circleFive),
        Card(CardShape.Circle, CardNumber.SEVEN, CardImages.circleSeven),
        Card(CardShape.Circle, CardNumber.EIGHT, CardImages.circleEight),
        Card(CardShape.Circle, CardNumber.TEN, CardImages.circleTen),
        Card(CardShape.Circle, CardNumber.ELEVEN, CardImages.circleEleven),
        Card(CardShape.Circle, CardNumber.TWELVE, CardImages.circleTwelve),
        Card(CardShape.Circle, CardNumber.FOURTEEN, CardImages.circleFourteen),

        /** King Card **/
        Card(CardShape.King, CardNumber.TWENTY, CardImages.kingCard),

        // Squares
        Card(CardShape.Square, CardNumber.ONE, CardImages.squareOne),
        Card(CardShape.Square, CardNumber.TWO, CardImages.squareTwo),
        Card(CardShape.Square, CardNumber.THREE, CardImages.squareThree),
        Card(CardShape.Square, CardNumber.FIVE, CardImages.squareFive),
        Card(CardShape.Square, CardNumber.SEVEN, CardImages.squareSeven),
        Card(CardShape.Square, CardNumber.TEN, CardImages.squareTen),
        Card(CardShape.Square, CardNumber.ELEVEN, CardImages.squareEleven),
        Card(CardShape.Square, CardNumber.FOURTEEN, CardImages.squareFourteen),

        /** King Card **/
        Card(CardShape.King, CardNumber.TWENTY, CardImages.kingCard),

        /** Crosses **/
        Card(CardShape.Cross, CardNumber.ONE, CardImages.crossOne),
        Card(CardShape.Cross, CardNumber.TWO, CardImages.crossTwo),
        Card(CardShape.Cross, CardNumber.THREE, CardImages.crossThree),
        Card(CardShape.Cross, CardNumber.FIVE, CardImages.crossFive),
        Card(CardShape.Cross, CardNumber.SEVEN, CardImages.crossSeven),
        Card(CardShape.Cross, CardNumber.TEN, CardImages.crossTen),
        Card(CardShape.Cross, CardNumber.ELEVEN, CardImages.crossEleven),
        Card(CardShape.Cross, CardNumber.THIRTEEN, CardImages.crossThirteen),
        Card(CardShape.Cross, CardNumber.FOURTEEN, CardImages.crossFourteen),

        /** King Card **/
        Card(CardShape.King, CardNumber.TWENTY, CardImages.kingCard),


                /** Star **/
        Card(CardShape.Star, CardNumber.ONE, CardImages.starOne),
        Card(CardShape.Star, CardNumber.TWO, CardImages.starTwo),
        Card(CardShape.Star, CardNumber.THREE, CardImages.starThree),
        Card(CardShape.Star, CardNumber.FOUR, CardImages.starFour),
        Card(CardShape.Star, CardNumber.FIVE, CardImages.starFive),
        Card(CardShape.Star, CardNumber.SEVEN, CardImages.starSeven),
        Card(CardShape.Star, CardNumber.TEN, CardImages.starTen),
        Card(CardShape.Star, CardNumber.ELEVEN, CardImages.starEleven),
        Card(CardShape.Star, CardNumber.THIRTEEN, CardImages.starThirteen),
        Card(CardShape.Star, CardNumber.FOURTEEN, CardImages.starFourteen),

        /** king card **/

        Card(CardShape.King, CardNumber.TWENTY, CardImages.kingCard),

        /** Triangles **/

        Card(CardShape.Triangle, CardNumber.ONE, CardImages.triangleOne),
        Card(CardShape.Triangle, CardNumber.TWO, CardImages.triangleTwo),
        Card(CardShape.Triangle, CardNumber.THREE, CardImages.triangleThree),
        Card(CardShape.Triangle, CardNumber.FOUR, CardImages.triangleFour),
        Card(CardShape.Triangle, CardNumber.FIVE, CardImages.triangleFive),
        Card(CardShape.Triangle, CardNumber.SEVEN, CardImages.triangleSeven),
        Card(CardShape.Triangle, CardNumber.EIGHT, CardImages.triangleEight),
        Card(CardShape.Triangle, CardNumber.THIRTEEN, CardImages.triangleThirteen),
        Card(CardShape.Triangle, CardNumber.FOURTEEN, CardImages.triangleFourteen),
    )
}

val cardService: IGameCardService = ServiceLocator.cardService