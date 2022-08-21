const Sequealize = require('sequelize');
const sequealize = require('../utils/database');

const Expense = sequealize.define("EXPENSE",{
    Id:{
        type : Sequealize.INTEGER,
        autoIncrement : true,
        primaryKey : true
    },
    ExpenseType : {
        type : Sequealize.STRING,
    },
    Desc : {
        type : Sequealize.STRING,
    },
    Amount : {
        type : Sequealize.INTEGER
    },
    Date : {
        type : Sequealize.STRING
    },
    Color : {
        type : Sequealize.STRING,
    },
});

module.exports = Expense;