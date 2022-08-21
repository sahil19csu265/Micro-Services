const Expense = require('../../model/expense.js');

const expenseServices = {
    async add(expenseObj){
        const expense = await Expense.create(
            {
                ExpenseType : expenseObj.type,
                Desc : expenseObj.desc,
                Amount : parseInt(expenseObj.amount),
                Date : expenseObj.date,
                Color : expenseObj.color
            }
        );
        expense.save();
    },
    async read(){
        const expenseList = await Expense.findAll();
        return expenseList;
    },
    async update(Id,expenseObj){
        try {
            const response = await Expense.update(
              {
                  ExpenseType : expenseObj['type'],
                  Desc : expenseObj['desc'],
                  Amount : expenseObj['amount'],
                  Date : expenseObj['date'],
                  Color : expenseObj['color']
              },
              { where: { Id: Id } }
            );
            return response;
            
        } catch (err) {
            console.log(`Error updating the record`);
        }
    },
    async delete(Id){
        try{
            const response = await Expense.destroy(
                {where : {Id : Id}}
            );
            return response;
        }catch (err) {
            console.log(`Error deleting the record`);
        }
    }
        
}

module.exports = expenseServices;