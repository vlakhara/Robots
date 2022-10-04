package com.vipul.restDemo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AlienResource {

	AlienRepository repo = new AlienRepository();

	@GetMapping("Aliens")
	public List<Alien> getAliens() {
		return repo.getAliens();
	}

	// create alien
	@PostMapping("Aliens/NewAlien")
	public Alien createAlien(@RequestBody Alien a) {
		repo.createAlien(a);
		return a;
	}

	// get single alien
	@GetMapping("Aliens/{id}")
	public Alien getAlien(@PathVariable int id) {

		return repo.getAlien(id);
	}

	@DeleteMapping("Aliens/{id}")
	public String delAlien(@PathVariable int id) {
		return repo.delAlien(id);
	}

	@PutMapping("Aliens/UpAlien")
	public Alien upAlien(@RequestBody Alien a) {
		return repo.upAlien(a);
	}
}
