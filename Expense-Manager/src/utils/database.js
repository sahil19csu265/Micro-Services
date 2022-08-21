const Sequealize = require('sequelize');
require("dotenv").config();

const sequealize = new Sequealize(
    process.env.DATABASE,
    process.env.DB_USERNAME,
    process.env.DB_PASSWORD,
    {
        dialect : process.env.DB_DIALECT,
        host : process.env.DB_HOST,
        define : {
            freezeTableName: true,
            createdAt: false,
            updatedAt: false,
        },
        logging : false
    }
);

module.exports = sequealize;