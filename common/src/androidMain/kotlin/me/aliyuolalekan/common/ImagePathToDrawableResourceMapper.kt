package me.aliyuolalekan.common

import me.aliyuolalekan.common.domain.model.ImagePath
import me.aliyuolalekan.common.ingame.data.CardImages
import me.aliyuolalekan.common.logger.logger

private const val RESOURCE_NOT_FOUND = -1

object ImagePathToDrawableResourceMapper {
    fun mapPathToResource(imagePath: ImagePath): Int =
        when (imagePath) {

            // Circles path to drawable Mapper

            CardImages.circleOne -> R.drawable.circle_one
            CardImages.circleTwo -> R.drawable.circle_two
            CardImages.circleThree -> R.drawable.circle_three
            CardImages.circleFour -> R.drawable.circle_four
            CardImages.circleFive -> R.drawable.circle_five
            CardImages.circleSeven -> R.drawable.circle_seven
            CardImages.circleEight -> R.drawable.circle_eight
            CardImages.circleTen -> R.drawable.circle_ten
            CardImages.circleEleven -> R.drawable.circle_eleven
            CardImages.circleTwelve -> R.drawable.circle_twelve
            CardImages.circleFourteen -> R.drawable.circle_fourteen

            // Square path to drawable Mapper

            CardImages.squareOne -> R.drawable.square_one
            CardImages.squareTwo -> R.drawable.square_two
            CardImages.squareThree -> R.drawable.square_three
            CardImages.squareFive -> R.drawable.square_five
            CardImages.squareSeven -> R.drawable.square_seven
            CardImages.squareTen -> R.drawable.square_ten
            CardImages.squareEleven -> R.drawable.square_eleven
            CardImages.squareFourteen -> R.drawable.square_fourteen

            // Triangle path to drawable Mapper

            CardImages.triangleOne -> R.drawable.triangle_one
            CardImages.triangleTwo -> R.drawable.triangle_two
            CardImages.triangleThree -> R.drawable.triangle_three
            CardImages.triangleFour -> R.drawable.triangle_four
            CardImages.triangleFive -> R.drawable.triangle_five
            CardImages.triangleSeven -> R.drawable.triangle_seven
            CardImages.triangleEight -> R.drawable.triangle_eight
            CardImages.triangleThirteen -> R.drawable.triangle_thirteen
            CardImages.triangleFourteen -> R.drawable.triangle_fourteen

            // Cross path to drawable Mapper

            CardImages.crossOne -> R.drawable.cross_one
            CardImages.crossTwo -> R.drawable.cross_two
            CardImages.crossThree -> R.drawable.cross_three
            CardImages.crossFive -> R.drawable.cross_five
            CardImages.crossSeven -> R.drawable.cross_seven
            CardImages.crossTen -> R.drawable.cross_ten
            CardImages.crossEleven -> R.drawable.cross_eleven
            CardImages.crossThirteen -> R.drawable.cross_thirteen
            CardImages.crossFourteen -> R.drawable.cross_fourteen

            // Stars path to drawable Mapper

            CardImages.starOne -> R.drawable.star_one
            CardImages.starTwo -> R.drawable.star_two
            CardImages.starThree -> R.drawable.star_three
            CardImages.starFour -> R.drawable.star_four
            CardImages.starFive -> R.drawable.star_five
            CardImages.starSeven -> R.drawable.star_seven
            CardImages.starTen -> R.drawable.star_ten
            CardImages.starEleven -> R.drawable.star_eleven
            CardImages.starThirteen -> R.drawable.star_thirteen
            CardImages.starFourteen -> R.drawable.star_fourteen

            CardImages.kingCard -> R.drawable.king
            CardImages.playerOne -> R.drawable.player_one
            CardImages.playerTwo -> R.drawable.player_two

            // back of card

            CardImages.cardBack -> R.drawable.card_back

            GlobalResources.topBannerImage -> R.drawable.top_banner
            GlobalResources.gamePlayImage -> R.drawable.game_play
            else -> {
                logger.error("Resource not found for image path - $imagePath")
                -RESOURCE_NOT_FOUND
            }
        }
}