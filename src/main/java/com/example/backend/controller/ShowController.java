package com.example.backend.controller;

import com.example.backend.model.Show;
import com.example.backend.repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
// wire the path
public class ShowController {
  /*
  – @CrossOrigin is for configuring allowed origins.
  – @RestController annotation is used to define a controller and to indicate that the return value of the methods should be be bound to the web response body.
  – @RequestMapping("/api") declares that all Apis’ url in the controller will start with /api.
  – We use @Autowired to inject ShowRepository bean to local variable.
   */
  @Autowired
  ShowRepo showRepo;
  /*
  https://www.baeldung.com/spring-new-requestmapping-shortcuts
  @GetMapping
  @PostMapping
  @PutMapping
  @DeleteMapping
  @PatchMapping
 */
  @GetMapping("/shows")
  public ResponseEntity<List<Show>> getAllShows(@RequestParam(required = false) String title) {
    try {
      List<Show> shows = new ArrayList<Show>();
      if (title == null)
        showRepo.findAll().forEach(shows::add);
      else
        showRepo.findByTitleContaining(title).forEach(shows::add);
      if (shows.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(shows, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/shows/{id}")
  public ResponseEntity<Show> getShowById(@PathVariable("id") long id) {
    Optional<Show> showData = showRepo.findById(id);
    if (showData.isPresent()) {
      return new ResponseEntity<>(showData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/shows")
  public ResponseEntity<Show> createShow(@RequestBody Show show) {
    try {
      Show _show = showRepo.save(new Show(show.getTitle(), show.getDescription(), show.getNetwork(), show.getWeekday(),
                      show.isStatus()));
      return new ResponseEntity<>(_show, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/shows/{id}")
  public ResponseEntity<Show> updateShow(@PathVariable("id") long id, @RequestBody Show show) {
    Optional<Show> showData = showRepo.findById(id);

    if (showData.isPresent()) {
      Show _show = showData.get();
      _show.setTitle(show.getTitle());
      _show.setDescription(show.getDescription());
      _show.setNetwork(show.getNetwork());
      _show.setWeekday(show.getWeekday());
      _show.setStatus(show.isStatus());
      return new ResponseEntity<>(showRepo.save(_show), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/shows/{id}")
  public ResponseEntity<HttpStatus> deleteShow(@PathVariable("id") long id) {
    try {
      showRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/shows")
  public ResponseEntity<HttpStatus> deleteAllShows() {
    try {
      showRepo.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/shows/published")
  public ResponseEntity<List<Show>> findByStatus() {
    try {
      List<Show> shows = showRepo.findByStatus(true);

      if (shows.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(shows, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}