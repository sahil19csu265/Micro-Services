const Expense = require('../model/expense');
const sequealize = require('../utils/database');

const db = {
    connect(){
        sequealize
            .sync({force:true})
            .then(
                (result)=>{
                    console.log(`Database Established & Connected...`);
                }
            )
            .catch(
                (err)=>{
                    console.log(`Error Establishing Database : ${err}`);
                }
            );
    }
}

module.exports = db;
