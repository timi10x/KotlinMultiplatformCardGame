const bcrypt = require('bcrypt')
const saltRounds = 10

const hash = (password, callback) => {
    bcrypt.hash(password, saltRounds, (error, encrypted) => {
        if (error) {
            callback(null)
        } else {
            callback(encrypted)
        }
    })
}

const compare = (password, hash, callback) => {
    bcrypt.compare(password, hash, (error, result) => {
        if (error) {
            callback(null) 
        } else {
            callback(result)
        }
    })
}

module.exports = { hash, compare }