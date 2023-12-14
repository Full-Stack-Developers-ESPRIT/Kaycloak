package Controller;


import Entity.User;
import Service.UserService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/list")
    public List<User> getAll (){
        return  userService.getAllUsers();
    }

    @GetMapping("getById/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
//-------------------------------------------------------------------

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create")

    public ResponseEntity<User> createCandidat(@RequestBody User user,
                                                   KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");
        if (hasUserRole) {
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    //--------------------------------------------------------------------------



        @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)

        public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
        @RequestBody User user){
            return new ResponseEntity<>(userService.updateUser(id, user),
                    HttpStatus.OK);
        }


        //------------------------------------------------------------------------------

    @DeleteMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCandidat(@PathVariable(value = "id") Long id,
                                                 KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("Admin");
        if(hasUserRole){
            return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }




}
