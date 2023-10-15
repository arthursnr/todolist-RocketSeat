package br.com.arthurribeiro.todolist.user;

import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Modificador de acesso
 * Public - Acesso irrestrito a partir de qualquer lugar.
 * Private - Acesso apenas dentro da classe.
 * Protected - Acesso dentro da classe e em subclasses no mesmo pacote.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    /**
     * String (texto)
     * Int (número) numeros inteiros
     * Double (double) numeros com casas decimais (15 - 16 caracteres)
     * Float (float) numeros decimais com menos precisos que o double (7 caracteres)
     * Char (A, C, a, c) caracteres
     * Date (data)
     * Void (void)
     */
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
       var user = this.userRepository.findByUsername(userModel.getUsername());
       
       if(user != null) {
        // Mensagem de  erro
        // Status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
       }
       var passwordHashred = BCrypt.withDefaults()
       .hashToString(12, userModel.getPassword().toCharArray());

       userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
