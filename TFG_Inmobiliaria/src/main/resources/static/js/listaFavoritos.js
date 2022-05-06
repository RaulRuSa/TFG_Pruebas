
const añadirFavoritos = document.querySelectorAll(".añadirFavoritos").forEach(item =>{
	item.addEventListener('click', event => {
		event.preventDefault();
	console.log('entro al js de añadir favoritos');
	console.log('usuario '+ usuario.textContent);
	
	//console.log(a.dataset.id);
	
	fetch(`http://localhost:4000/rest/inmobiliaria/buscarFavoritosUsuario/${usuario.textContent}`, {
		method:'GET',
		headers: {'Content-type': 'application/json'}
		})
		.then(response =>{
			if(response.status == 200){
				
				return response.json();
			}else{
				
				throw new Error(response.status);
			}	
		})
		.then(UsuarioJson => {
			console.log(UsuarioJson);
			
			
		})
		.catch(error => {
			console.error("Error: ", error.message);
		});
	})
});
