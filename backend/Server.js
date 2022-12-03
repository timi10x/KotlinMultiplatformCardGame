require('dotenv').config()
const mongoose = require('mongoose')
var express = require('express')
var bodyParser = require('body-parser')
const connectDb = require('./schema/Config')
const loginRoute = require('./routes/LoginRoute')
const signUpRoute = require('./routes/SignupRoute')

const PORT = process.env.PORT || 3500

connectDb()

const app = express()

app.use(bodyParser.urlencoded({ extended : true }))

app.use('/signup', signUpRoute)

app.use('/login', loginRoute)

mongoose.connection.once('open', () => {
    console.log("Connected to MongoDb")
    app.listen(PORT, () => { console.log(`Listening on port ${PORT}`) })
})