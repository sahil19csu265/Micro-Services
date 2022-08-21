export const ApiClient = {
    async addExpense(expenseObj){
        const response = await fetch(
            "http://localhost:9999/post-expense",
            {
                method: 'POST',
                mode : 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(expenseObj)
            }
        );
        return response;
    },
    displayExpense(){
        const response = fetch(
            "http://localhost:9999/read-expenses",
            {
                method: 'GET',
                mode : 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
            }
        );
        return response;
    },
    updateExpense(Id,expenseObj){
        const response = fetch(
            "http://localhost:9999/update-expense",
            {
                method: 'POST',
                mode : 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body : JSON.stringify({
                    Id : Id,
                    expenseObj : expenseObj
                })
            }
        );
        return response;
    },
    deleteExpense(Id){
        const response = fetch(
            "http://localhost:9999/delete-expense",
            {
                method: 'POST',
                mode : 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body : JSON.stringify({
                    Id : Id
                })
            }
        );
        return response;
    }
}