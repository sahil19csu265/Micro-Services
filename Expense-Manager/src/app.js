const db = require('./database/connect');
const express = require('express');
const app = express();
const cors = require('cors');
app.use(express.static("public"));
app.use(express.json());
require("dotenv").config();
const expenseServices = require('./database/services/expense.js');

db.connect();

app.use(cors());

app.use('/post-expense',(req,res)=>{
   const promise = expenseServices.add(req.body);
   promise.then(()=>{
       res.status(200).json({message:"Successfully Added Expense"})
   }).catch((err)=>{
       res.status(500).json({message:"Failed To Add Expense"})
   });
});

app.use('/read-expenses',(req,res)=>{
    const expenses = expenseServices.read();
    expenses.then((expenseList)=>{
        let result = expenseList.map((e)=>e['dataValues']);
        res.status(200).json({"result" : result});
    }).catch((err)=>{
        res.status(500).json();
    });
    
 });

 app.use('/update-expense',(req,res)=>{
    const promise = expenseServices.update(req.body.Id,req.body.expenseObj);
    promise.then(()=>{
        // res.redirect('/read-expenses');
        res.status(200).json();
    }).catch((err)=>{
        res.status(500).json();
    });
    
 });

 app.use('/delete-expense',(req,res)=>{
    const promise = expenseServices.delete(req.body.Id);
    promise.then(()=>{
        // res.redirect('/read-expenses');
        res.status(200).json();
    }).catch((err)=>{
        res.status(500).json();
    });
    
 });

const server = app.listen(process.env.PORT || 1234,(err)=>{
    if(err){
        console.log(`Server failed to start`);
    }
    else{
        console.log(`Server started at port ${server.address().port}`);
    }
})

