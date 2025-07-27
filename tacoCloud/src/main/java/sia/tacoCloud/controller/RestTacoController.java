package sia.tacoCloud.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.tacoCloud.dao.TacoRepository;
import sia.tacoCloud.data.taco.Taco;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = {"http://tacocloud:8080", "http://tacocloud.com"})
public class RestTacoController {

    private TacoRepository tacoRepo;

    public RestTacoController(TacoRepository tacoRepo){
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(
                0,12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public Optional<Taco> tacoById(@PathVariable Long id){
        return tacoRepo.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepo.save(taco);
    }
}
