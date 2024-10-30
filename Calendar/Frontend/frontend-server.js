const cors = require("cors");

const express = require('express')
const app = express()
const port = 3000

app.use(express.static(__dirname + '/dist'))
const allowedOrigins = ["http://localhost:3000", "http://localhost:8088"];


app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})
