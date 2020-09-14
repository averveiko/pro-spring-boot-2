@RestController
class WebApp{
	@GetMapping("/")
	String welcome() {
		"<h1><font face='vernada'>Spring Boot Rocks!</font></h1>"
	}
}