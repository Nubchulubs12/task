package Tasks.Controllers;

import Tasks.Models.JsonStorage;
import Tasks.Models.TaskItems;
import Tasks.Models.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @GetMapping
    public String showTasks(Model model, OAuth2AuthenticationToken token) {
        String email = token.getPrincipal().getAttribute("email");
        User currentUser = JsonStorage.loadUser(email);
        String name = token.getPrincipal().getAttribute("name");

        model.addAttribute("userName", name);
        model.addAttribute("userEmail", email);
        model.addAttribute("tasks", currentUser.getTasks());
        return "tasks";
    }



    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new TaskItems("", "", LocalDate.now()));
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute TaskItems task, OAuth2AuthenticationToken token) {
        String email = token.getPrincipal().getAttribute("email");
        User currentUser = JsonStorage.loadUser(email);

        currentUser.addTaskItems(task);
        JsonStorage.saveUser(currentUser);

        return "redirect:/tasks";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam("title") String title, OAuth2AuthenticationToken token) {
        String email = token.getPrincipal().getAttribute("email");
        User currentUser = JsonStorage.loadUser(email);

        currentUser.getTasks().removeIf(task -> task.getTitle().equals(title));
        JsonStorage.saveUser(currentUser);

        return "redirect:/tasks";
    }
}
