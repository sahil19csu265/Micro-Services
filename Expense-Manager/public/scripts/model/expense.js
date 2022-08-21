
export function Expense(type,desc,amount,date,color,Id){
    this.Id = Id ? Id : null;
    this.type = type,
    this.desc = desc,
    this.amount = amount,
    this.date = date,
    this.color = color
}