package br.com.arthurribeiro.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * SErve para definir a estrutura da tabela de tarefas, tudo o que for
 * relcionado as tarefas, vai ser colocado aqui dentro
 * ID
 * Usuário
 * Descrição
 * Título
 * Data de Início
 * Data de Término
 * Prioridade
 * 
 */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

     @Id
     @GeneratedValue(generator = "UUID")
     private UUID id;

     @CreationTimestamp
     private LocalDateTime createdAt;


     @Column(length = 50)
     private String title;
     private LocalDateTime startAt;
     private String description;
     private LocalDateTime endAt;
     private String priority;

     private UUID idUser;

     public void setTitle(String title) throws Exception{
          if(title.length() > 50){
               throw new Exception("O campo title deve conter no máximo 50 caractéres");
          }
          this.title = title;
     }
     

}
