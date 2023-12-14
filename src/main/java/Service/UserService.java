package Service;

import Entity.User;
import Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public List<User> getUsers() {


        return userRepo.findAll();
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    // Suppression (Delete)
    public String  deleteUser(Long id) {

        userRepo.deleteById(id);
        return  "c bon tfsakh";
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setNom(updatedUser.getNom());
            existingUser.setPrenom(updatedUser.getPrenom());
            existingUser.setPassword(updatedUser.getPassword());
            return userRepo.save(existingUser);
        }
        return null;
    }



}
