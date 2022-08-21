import {Expense} from '../model/expense.js';
import {CONSTANTS} from '../utils/constants.js';
import { ApiClient } from '../utils/apiclient.js';
const {ID} = CONSTANTS.KEYS;

export const expenseOperations = {
    expenses : [],
    add(fieldData){
        var expenseObject = new Expense();
        for(let key in fieldData){
            expenseObject[key] = fieldData[key];
        }
        return ApiClient.addExpense(expenseObject);
    },
    async read(){
        try{
            const response = await ApiClient.displayExpense();
            const expenseJSON = await response.json();
            this.expenses = expenseJSON['result'].map(
                (expense) =>
                     new Expense(expense['ExpenseType'],expense['Desc'],expense['Amount'],expense['Date'],expense['Color'],expense['Id'])
            );
        }
        catch(err){
            console.log(`Server error ${err}`);
        }
    },
    getTask(expenseID){
        return this.expenses.find(expense => expense[ID] == expenseID);
    },
    async update(Id,expenseObj){
        try{
           await ApiClient.updateExpense(Id,expenseObj);
           await this.read();
        }
        catch(err){
            console.log(`Server error ${err}`);
        }
    },
    async delete(expenseID){
        try{
           await ApiClient.deleteExpense(expenseID);
           await this.read();
        }
        catch(err){
            console.log(`Server error ${err}`);
        }
    },
    search(keyword){
        return this.expenses.filter(expense => (expense[ID] == keyword));
    },
}