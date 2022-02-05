package com.eduardocode.todolist.controller;

import com.eduardocode.todolist.model.Task;
import com.eduardocode.todolist.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class TaskController {

    TaskService taskService;

    @ApiOperation(value = "Criando uma nova tarefa")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Tarefa criada com sucessoo"),
            @ApiResponse(code = 500, message = "Houve um erro ao criar a tarefa, verifique as informações")
    })
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask (@RequestBody Task task) {
        log.info("Criando uma nova tarefa com as informações [{}]", task);
        return taskService.createTask(task);
    }

    @ApiOperation(value = "Listando todas as tarefas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tarefa listada com sucessoo"),
            @ApiResponse(code = 500, message = "Houve um erro ao lista as tarefas")
    })
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        log.info("Listando todas as tarefas cadastradas");
        return taskService.listAllTasks();
    }

    @ApiOperation(value = "Buscando uma tarefa pelo id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tarefa encontrada com sucessoo"),
            @ApiResponse(code = 404, message = "Não foi encontrada nenhuma tarefa com esse id")
    })
    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTasksById(@PathVariable (value = "id") Long id) {
        log.info("Buscando tarefa com o id [{}]", id);
        return taskService.findTaskById(id);
    }

    @ApiOperation(value = "Atualizando as informações da tarefa")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tarefa atualizada com sucessoo"),
            @ApiResponse(code = 404 , message = "Não foi possivel atualizar a tarefa - tarefa não encontrada")
    })
    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTasksById(@PathVariable (value = "id") Long id,
                                                @RequestBody Task task) {
        log.info("Atualizando a tarefa com id [{}] as novas informações são: [{}]", id, task);
        return taskService.updateTaskById(task,id);
    }

    @ApiOperation(value = "Criando uma nova tarefa")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Tarefa excluida com sucessoo"),
            @ApiResponse(code = 404, message = "Não foi possivel excluir a tarefa - tarefa não encontrada")
    })
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTasksById(@PathVariable (value = "id") Long id) {
        log.info("Excluindo tarefas com o id [{}]", id);
        return taskService.deleteById(id);
    }

}
