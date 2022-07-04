/**
 * Custom JS
 */

/* Botões editar e remover da view lista.jsp */
// pega o tipo da ação e o id e envia via url
 const action = (element, local)=> {
	const type = element.getAttribute("name");
	const itemId = element.parentElement.parentElement.getAttribute("name"); // tr - name
	window.location.href = `${local}?acao=${type}&id=${itemId}`;
}

const inputSearch = document.getElementById("inputSearch");

/* Barra de pesquisa da view lista.jsp */
const search = (element, local)=> {
	element.addEventListener("keypress", event => {
		if(event.key == "Enter") {
			event.preventDefault();
			window.location.href = `${local}?acao=buscar&buscar=${element.value}`;
		}
	});
}