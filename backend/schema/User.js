const mongoose = require('mongoose')
const Schema = mongoose.Schema

const userSchema = new Schema(
    {
        nickname: { type: String, required: true },
        email: { type: String, required: true },
        password: {type: String, required: true },
        accessToken: { type: String, required: false },
        friends: { type: Array, required: false },
        games: { type: Array, required: false }
    }
)

module.exports = mongoose.model('user', userSchema)