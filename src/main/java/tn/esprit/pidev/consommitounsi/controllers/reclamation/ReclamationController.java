package tn.esprit.pidev.consommitounsi.controllers.reclamation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.reclamation.Reclamation;
import tn.esprit.pidev.consommitounsi.services.reclamation.IReclamationService;

import java.util.List;

@RestController
public class ReclamationController {
    @Autowired
    IReclamationService reclamationService;

    @PostMapping("/AddReclamation")
    public Reclamation addReclamation (@RequestBody Reclamation reclamation){
        reclamationService.addOrUpdate(reclamation);
        return reclamation;
    }

    @GetMapping("/Reclamations")
    public List<Reclamation> getAllReclamations(){
        return reclamationService.getAll();
    }

    @GetMapping("/Reclamation/{id}")
    @ResponseBody
    public Reclamation getReclamatinoById(@PathVariable("id")long id) {
        return reclamationService.getById(id);
    }

    @DeleteMapping("/ReclamationDelete/{id}")
    @ResponseBody
    public void deleteReclamation(@PathVariable("id")long id) {
        reclamationService.delete(id);
    }
}
