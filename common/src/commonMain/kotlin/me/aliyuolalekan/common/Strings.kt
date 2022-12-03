package me.aliyuolalekan.common

object Strings {
    const val APP_NAME = "WHOT GAME"
    const val WON_MESSAGE = "You have won!"
    const val INVALID_CARD_SELECTED_MESSAGE = "Invalid Card Selected -"
    const val NEW_GAME = "New Game"
    const val MAIN_MENU = "Main Menu"

    // Shapes
    const val CIRCLE = "Circle"
    const val SQUARE = "Square"
    const val CROSS = "Cross"
    const val TRIANGLE = "Triangle"
    const val KING = "King"
    const val STAR = "Star"

    const val ABOUT_GAME_HEADER = "WhotPlus is a simple card game. Are you familiar with sprint race? " +
            "Well, that's what the game is about... Kinda 😛. " +
            "The person that plays all cards first wins the game! That's it. " +
            "However, let's get into some details 🙃."

    const val GAME_DESCRIPTION =
        "The game board comprises Player Cards at the bottom, Opponent position at center top, " +
                "Play Area at the center of the screen and Market Cards at the left of the screen." +
                "Market Cards are there for card refill, you can only pick one at a time if you don't have matching card." +
                " Okay, let's discuss the rules. \n"

    const val GAME_RULES = "The game plays in turns. A play is valid if Card Number or Card Shape " +
            "matches the card in play area. A King Card doesn't respect this rule. A king Card can be played " +
            "at any time during game play.\n" +
            "Some cards have fire power, such as cards with Numbers (2, 5, 8, 14 and 20).\nTo some fun fire power!🤠"

    const val PICK_TWO_TITLE = "Pick Two"
    const val FIRE_POWER_2 =
        "Cards with number 2 will automatically add two more cards to the opponent's cards when played and will give " +
                "the current player another chance to play."

    const val PICK_THREE_TITLE = "Pick Three"
    const val FIRE_POWER_5 =
        "Cards with number 5 will automatically add three more cards to the opponent's cards when played and will give " +
                "the current player another chance to play."

    const val HOLD_ON_TITLE = "Hold On"
    const val FIRE_POWER_8 =
        "Cards with number 8 will give the current player another play chance when played. Meaning double play chances!"

    const val GENERAL_MARKET_TITLE = "General Market"
    const val FIRE_POWER_14 =
        "Cards with number 14 will automatically add one more card to the opponent's card when played and will give" +
                " the current player another chance to play."

    const val KING_CARD =
        "Cards with number 20 will give the current player the chance to select one of his cards as a request for" +
                " the opponent to play. Be careful with using frequently, it can be very useful during end-game!"

    const val KING_DESCRIPTION_TITLE = "King Card"

    const val ABOUT_GAME_TITLE = "About Game"
}