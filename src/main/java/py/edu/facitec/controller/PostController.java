package py.edu.facitec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.model.Post;
import py.edu.facitec.respository.PostRepository;


@RestController
@RequestMapping("/api")
public class PostController {

	
	@Autowired
	private PostRepository postRepository;
		
		@GetMapping("/posts")
		public ResponseEntity<List<Post>> getPost() {
			
			List<Post> posts = postRepository.findAll();
			
			
			return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);	
		}
		
		
		@PostMapping("/post")
		public ResponseEntity<Post> guardarPost(@RequestBody Post post){
			
			postRepository.save(post);
			
			return new ResponseEntity<Post>(post, HttpStatus.OK);	 
		}
		
		@GetMapping("/post/{id}")
		public ResponseEntity<Post> getOnePost(@PathVariable Long id) {
			Optional<Post> posRetorno = postRepository.findById(id);
			
			if (posRetorno.isPresent()) {
				return new ResponseEntity<Post>(posRetorno.get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		}
		
		
		
		@DeleteMapping("/post/{id}")
		public ResponseEntity<Post> removeOnePost(@PathVariable Long id) {

			Optional<Post> posRetorno = postRepository.findById(id);
			// si encuentra
			if (posRetorno.isPresent()) {

				postRepository.deleteById(id);

				return new ResponseEntity<>(HttpStatus.OK);

			} else { // si no se encuentra por la id

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}

		}
}
