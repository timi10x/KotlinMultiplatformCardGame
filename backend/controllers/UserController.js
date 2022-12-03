const User = require('.././schema/User')
const jwt = require('jsonwebtoken')
const mongoose = require('mongoose')
const hash = require('./hash/Hash')

let getMissingField = (nickname, email, password) => {
    if(!nickname && !email && !password) {
        return "nickname, email and password"
    }
    if (!nickname && !email) {
        return "nickname and email"
    }
    if (!nickname && password) {
        return "nickname and password"
    }

    if (!email && !password) {
        return "email and password"
    }

    return ""
}

const handleSignUp = async (req, res, next) => {
    const body = req.body
    const nickname = body.nickname
    const email = body.email
    const password = body.password

    if (!nickname || !email || !password)
        return res.sendStatus(400).json({ 'message' : `missing  field ${getMissingField(nickname, email, password)}` })

    const duplicate = await User.findOne( {email : email} ).exec()

    if (duplicate) res.sendStatus(409)

    hash.hash(password, async (result) => {
        if(result === null) {
            return res.sendStatus(409).json({ 'message' : `invalid password` })
        } else {
            try{
                await User.create(
                    {
                        "nickname": nickname,
                        "email": email,
                        "password": result
                    }
                )
                next()
            }catch(error) {
                res.status(500).json({ 'message' : error.message })
            }
        }
    })
}

const buildUser = (user) => {
    return {
        "nickname": user.nickname,
        "email": user.email,
        "accessToken": user.accessToken
    }
}

const handleLogin = async (req, res) => {
    const body = req.body
    const email = body.email
    const password = body.password
    
    if (!email || !password) return res.sendStatus(400).json({ 'message' : 'missing  field found}' })

    const user = await User.findOne( {email : email} ).exec()

    if (!user) {
        return res.sendStatus(404).json({ 'message' : `user with email ${email} not found` })
    }

    hash.compare(password, user.password, async (data) => {
        if (data === null) {
            return res.sendStatus(409).json({ 'message' : `invalid password` })
        } else {
            const accessToken = jwt.sign(
                { "username" : user.nickname },
                process.env.ACCESS_TOKEN_SECRET
            )
            
            console.log("Access Token: " + accessToken)

            const filter = { email: email }
            const update = { accessToken: accessToken }
        
            let updatedUser = await User.findOneAndUpdate(filter, update)
            updatedUser.accessToken = accessToken

            console.log("Updated User " + updatedUser)
            
            res.status(200).json(buildUser(updatedUser))
        }
    })
}

module.exports = { handleSignUp, handleLogin }



