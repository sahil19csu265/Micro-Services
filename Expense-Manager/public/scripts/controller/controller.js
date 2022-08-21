import { expenseOperations } from "../services/service.js";
import {CONSTANTS} from '../utils/constants.js';
const {FONT_AWESOME_BASE,SIZE,TRASH,EDIT,HAND,MARGIN_RIGHT,DANGER} = CONSTANTS.CLASSES;
const {COLOR_CIRCLE,EXPENSES,SEARCH_BTN,CLEAR_BTN,ADD_BTN,SEARCH_BOX,
  UPDATE_BTN,LOAD_CLOUD_BTN} = CONSTANTS.ID;
const {ID,TYPE,DESC,AMOUNT,DATE,COLOR} = CONSTANTS.KEYS;

window.addEventListener("load", init);

let expense_fields  = [TYPE,DESC,AMOUNT,DATE,COLOR];
let currSelectedID;

function init(){
    bindEvents();
    //loadFromServer();
}

function bindEvents() {
  document.querySelector(`#${ADD_BTN}`).addEventListener("click", addExpense);
  document.querySelector(`#${UPDATE_BTN}`).addEventListener("click",updateExpense);
  document.querySelector(`#${LOAD_CLOUD_BTN}`).addEventListener("click",loadFromServer);
  document.querySelector(`#${SEARCH_BTN}`).addEventListener("click",searchExpense);
  document.querySelector(`#${CLEAR_BTN}`).addEventListener("click",clearSearchResults);
}

function addExpense() {
  const fieldData = {}; // Object Literal
  for (let field of expense_fields ) {
    fieldData[field] = document.querySelector(`#${field}`).value;
  }
  const response = expenseOperations.add(fieldData);
  response.then(
    ()=>{
      alert(`Expense Added`);
      loadFromServer();
    },
    () =>
      alert(`Expense Failed to Add`)
  );
}

async function loadFromServer(){
  await expenseOperations.read();
  printTable(expenseOperations.expenses);
}

function printTable(expenseList){
  document.querySelector(`#${EXPENSES}`).innerHTML = "";
  expenseList.forEach((expense) => printTask(expense));
}

function printTask(expenseObject) {
  const tbody = document.querySelector(`#${EXPENSES}`);
  const tr = tbody.insertRow();
  let cellIndex = 0;
  for (let key in expenseObject) {
    if(key == COLOR){
      tr.insertCell(cellIndex).appendChild(createColor(expenseObject[key]));
    }
    else{
        tr.insertCell(cellIndex).innerText = expenseObject[key];
    }
    cellIndex++;
  }
  const td = tr.insertCell(cellIndex);
  td.appendChild(createIcon(`${TRASH} ${MARGIN_RIGHT}`,deleteExpense,expenseObject[ID]));
  td.appendChild(createIcon(EDIT,editTask,expenseObject[ID]));
}

function createIcon(iconClass, fn, taskID){ 
  const icon = document.createElement('i');
  icon.className = `${HAND} ${FONT_AWESOME_BASE} ${iconClass}`;
  icon.addEventListener('click',fn);
  // custom attribute to facilitate delete and edit
  icon.setAttribute(ID,taskID);
  return icon;
}

function editTask(){
  const expenseID = this.getAttribute(ID);
  const expenseObj = expenseOperations.getTask(expenseID);
  showTaskOnForm(expenseObj);
}

function showTaskOnForm(expenseObj){
  clearForm();
  for(let key in expenseObj){
    if(key == ID){
      currSelectedID = expenseObj[key];
      continue;
    }
    document.querySelector(`#${key}`).value = expenseObj[key];
  }
}

async function updateExpense(){
  const expenseObj = {};
  for(let field of expense_fields ){
    expenseObj[field] = document.querySelector(`#${field}`).value;
  }
  await expenseOperations.update(currSelectedID,expenseObj);
  clearForm();
  printTable(expenseOperations.expenses);
}

async function deleteExpense(){
  const expenseID = this.getAttribute(ID);
  await expenseOperations.delete(expenseID);
  printTable(expenseOperations.expenses);
}

function clearForm(){
  for (let field of expense_fields ) {
    if(field === COLOR){
      document.querySelector(`#${field}`).value = '#000000';
    }
    else if(field == ID){

    }
    else{
      document.querySelector(`#${field}`).value = '';
    }
  }
}

// Js also allow you to create DOM structure dynamically
function createColor(color){
    const circle = document.createElement('div');
    circle.id = COLOR_CIRCLE;
    circle.className = 'rounded-circle';
    circle.style.background = color;
    return circle; 
}


function searchExpense(){
  const keyword = document.querySelector(`#${SEARCH_BOX}`).value;
  const searchedExpense = expenseOperations.search(keyword);
  if(searchedExpense){
    printTable(searchedExpense);
  }
}

function clearSearchResults(){
  document.querySelector(`#${SEARCH_BOX}`).value = "";
  printTable(expenseOperations.expenses);
}
