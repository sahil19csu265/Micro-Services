package Controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import daoImpl.TodoImpl;
import model.Todo;

public class ExpenseController extends HttpServlet {

		Todo Expense = new Todo();
        TodoDaoImpl TodoImpl = new TodoImpl();
        TodoDao edao;
       
  
    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
    if(result.hasErrors()) {
        return "todo";
    }
    todoService.addTodo(getLoggedInUsername(), todo.getDesc(), todo.getTargetDate(), false);
    return "redirect:/list-todos";
}

@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
public String showTodos(ModelMap model) {
    String name = getLoggedInUsername();
    model.put("todos", todoService.retrieveTodos(name));
    return "list-todos";
}

@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
    Todo taskToUpdate = todoService.getTodo(id);
    model.addAttribute("todo", taskToUpdate);
    return "todo";
}

@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
public String deleteTodo(ModelMap model, @RequestParam int id) {
    todoService.deleteTodo(id);
    return "redirect:/list-todos";
}


 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
